package com.example.LearnHub.mapper;

import com.example.LearnHub.dto.EnrollmentCreateDTO;
import com.example.LearnHub.dto.EnrollmentResponseDTO;
import com.example.LearnHub.model.Course;
import com.example.LearnHub.model.Enrollment;
import com.example.LearnHub.model.User;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {
    public Enrollment toEntity(EnrollmentCreateDTO dto, User user, Course course) {
        if (dto == null) return null;
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        return enrollment;
    }

    public EnrollmentResponseDTO toDto(Enrollment enrollment) {
        if (enrollment == null) return null;

        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setUserId(enrollment.getUser().getId());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setEnrolledAt(enrollment.getCreatedAt());
        return dto;
    }

}
