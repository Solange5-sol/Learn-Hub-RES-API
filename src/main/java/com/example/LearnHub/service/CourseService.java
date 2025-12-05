package com.example.LearnHub.service;

import com.example.LearnHub.dto.CourseCreateDTO;
import com.example.LearnHub.dto.CourseResponseDTO;
import com.example.LearnHub.exception.CourseNotFoundException;
import com.example.LearnHub.mapper.CourseMapper;
import com.example.LearnHub.model.Course;
import com.example.LearnHub.model.Role;
import com.example.LearnHub.model.User;
import com.example.LearnHub.repository.CourseRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {
    private CourseRepository courseRepository;
    private CourseMapper courseMapper;
    public CourseService(CourseRepository courseRepository,  CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }
    public CourseResponseDTO createCourse(CourseCreateDTO createDTO, User user) {
        if (user.getRole() != Role.ADMIN && user.getRole() != Role.INSTRUCTOR) {
            throw new IllegalArgumentException("Only instructors or admins can create courses");
        }
        Course course =courseMapper.toEntity(createDTO);
        course.setUser(user);
        Course saved= courseRepository.save(course);
        return courseMapper.toDto(saved);
    }

    public List<CourseResponseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).toList();
    }
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        return courseMapper.toDto(course);
    }

     public CourseResponseDTO updateCourse(Long id,CourseCreateDTO createDTO, User user) {
         Course course = courseRepository
                 .findById(id)
                 .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
         if(! course.getUser().getId().equals(user.getId())){
             throw new IllegalArgumentException("Wrong User");
         }
         course.setName(createDTO.getName());
         course.setDescription(createDTO.getDescription());
         Course update = courseRepository.save(course);
         return courseMapper.toDto(update);
     }
     public CourseResponseDTO deleteCourse(Long id, User user) {
        Course course=courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        if(! course.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("you are not creator of this  Course");
        }
        courseRepository.delete(course);
        return courseMapper.toDto(course);

     }

}
