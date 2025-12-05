package com.example.LearnHub.dto;

import java.time.LocalDateTime;

public class EnrollmentResponseDTO {
    private Long userId;
    private Long courseId;
    private LocalDateTime enrolledAt;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public LocalDateTime getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(LocalDateTime enrolledAt) {this.enrolledAt = enrolledAt;}

}
