package com.example.LearnHub.service;

import com.example.LearnHub.dto.LessonCreateDTO;
import com.example.LearnHub.dto.LessonResponseDTO;
import com.example.LearnHub.exception.CourseNotFoundException;
import com.example.LearnHub.mapper.LessonMapper;
import com.example.LearnHub.model.Course;
import com.example.LearnHub.model.Lesson;
import com.example.LearnHub.model.Role;
import com.example.LearnHub.model.User;
import com.example.LearnHub.repository.CourseRepository;
import com.example.LearnHub.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;
    public LessonService(LessonRepository lessonRepository,
                         CourseRepository courseRepository,
                         LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.lessonMapper = lessonMapper;
    }
    public LessonResponseDTO createLesson(Long courseId, LessonCreateDTO dto, User user) {
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.INSTRUCTOR) {
            throw new IllegalArgumentException("Only instructors or admins can create courses");
        }
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        if(!course.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Wrong User");
        }
        Lesson lesson=lessonMapper.toEntity(dto, course);
        Lesson save = lessonRepository.save(lesson);
        return lessonMapper.toDTO(save);
    }
    public List<LessonResponseDTO> getAllLessons(Long courseId) {
        return lessonRepository.findByCourseId(courseId)
                .stream()
                .map(lessonMapper::toDTO)
                .toList();
    }
    public LessonResponseDTO getLessonById(Long id) {
        Lesson lesson=lessonRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Lesson not found"));
        return lessonMapper.toDTO(lesson);
    }
    public LessonResponseDTO updateLesson(Long id, LessonCreateDTO dto, User user) {
        Lesson lesson=lessonRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("Lesson not found"));
        if(!lesson.getCourse().getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Wrong User");
        }
        lesson.setTitle(dto.getTitle());
        lesson.setContent(dto.getContent());
        Lesson save = lessonRepository.save(lesson);
        return lessonMapper.toDTO(save);
    }
    public void deleteLesson(Long id, User user) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        if(!lesson.getCourse().getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Wrong User");
        }
        lessonRepository.delete(lesson);
    }
}
