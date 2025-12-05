package com.example.LearnHub.controller;

import com.example.LearnHub.dto.EnrollmentCreateDTO;
import com.example.LearnHub.dto.EnrollmentResponseDTO;
import com.example.LearnHub.model.User;
import com.example.LearnHub.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<EnrollmentResponseDTO> enrollUser(
            @PathVariable Long courseId,
            @RequestBody EnrollmentCreateDTO dto,
            @AuthenticationPrincipal User user) {
        EnrollmentResponseDTO response = enrollmentService.enrollUser(courseId, user, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<List<EnrollmentResponseDTO>> getMyEnrollments(
            @AuthenticationPrincipal User user) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.getEnrollmentsByUser(user.getId());
        return ResponseEntity.ok(enrollments);
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentsByCourse(
            @PathVariable Long courseId) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        return ResponseEntity.ok(enrollments);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<EnrollmentResponseDTO> deleteEnrollment(
            @PathVariable Long enrollmentId,
            @AuthenticationPrincipal User user) {
        EnrollmentResponseDTO deleted = enrollmentService.deleteEnrollment(enrollmentId, user);
        return ResponseEntity.ok(deleted);
    }
}
