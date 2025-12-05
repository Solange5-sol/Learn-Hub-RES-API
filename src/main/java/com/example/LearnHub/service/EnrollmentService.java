package com.example.LearnHub.service;

import com.example.LearnHub.dto.EnrollmentCreateDTO;
import com.example.LearnHub.dto.EnrollmentResponseDTO;
import com.example.LearnHub.exception.CourseNotFoundException;
import com.example.LearnHub.exception.EnrollmentNotFoundException;
import com.example.LearnHub.exception.InvalidTokenException;
import com.example.LearnHub.mapper.EnrollmentMapper;
import com.example.LearnHub.model.Course;
import com.example.LearnHub.model.Enrollment;
import com.example.LearnHub.model.Role;
import com.example.LearnHub.model.User;
import com.example.LearnHub.repository.CourseRepository;
import com.example.LearnHub.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             CourseRepository courseRepository,
                             EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentMapper = enrollmentMapper;
    }
    public EnrollmentResponseDTO enrollUser(Long courseId, User user, EnrollmentCreateDTO dto) {
        if(user == null) {
            throw new InvalidTokenException("Missing or invalid Authorization header");
        }
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        if (enrollmentRepository.existsByUserIdAndCourseId(user.getId(), courseId)) {
            throw new IllegalArgumentException("User is already enrolled in this course");
        }

        Enrollment enrollment = enrollmentMapper.toEntity(dto, user, course);
        Enrollment saved = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toDto(saved);
    }
    public List<EnrollmentResponseDTO> getEnrollmentsByUser(Long userId) {
        return enrollmentRepository.findByUserId(userId)
                .stream()
                .map(enrollmentMapper::toDto)
                .toList();
    }

    public List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(enrollmentMapper::toDto)
                .toList();
    }
    public EnrollmentResponseDTO deleteEnrollment(Long enrollmentId, User user) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + enrollmentId));

        if (!enrollment.getUser().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("You have no  permission to delete this enrollment");
        }

        enrollmentRepository.delete(enrollment);
        return enrollmentMapper.toDto(enrollment);
    }
}
