package com.example.LearnHub.mapper;

import com.example.LearnHub.dto.CourseCreateDTO;
import com.example.LearnHub.dto.CourseResponseDTO;
import com.example.LearnHub.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toEntity(CourseCreateDTO dto) {
        if (dto == null) return null;
        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        return course;
    }

    public CourseResponseDTO toDto(Course course) {
        if (course== null) return null;
        CourseResponseDTO res = new CourseResponseDTO();
        res.setId(course.getId());
        res.setName(course.getName());
        res.setDescription(course.getDescription());
        res.setCreatedAt(course.getCreatedAt());
        res.setUserId(course.getUser() != null ? course.getUser().getId() : null);
        res.setComments(course.getComments());
        res.setLessons(course.getLessons());
        res.setEnrollments(course.getEnrollments());
        return res;

    }
}
