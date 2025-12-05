package com.example.LearnHub.mapper;

import com.example.LearnHub.dto.LessonCreateDTO;
import com.example.LearnHub.dto.LessonResponseDTO;
import com.example.LearnHub.model.Course;
import com.example.LearnHub.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toEntity(LessonCreateDTO dto, Course course) {
        Lesson lesson = new Lesson();
        lesson.setTitle(dto.getTitle());
        lesson.setContent(dto.getContent());
        lesson.setCourse(course);
        return lesson;
    }

    public LessonResponseDTO toDTO(Lesson lesson) {
        LessonResponseDTO dto = new LessonResponseDTO();
        dto.setId(lesson.getId());
        dto.setTitle(lesson.getTitle());
        dto.setContent(lesson.getContent());
        dto.setCreatedAt(lesson.getCreatedAt());
        dto.setCourseId(lesson.getCourse().getId());
        return dto;
    }
}
