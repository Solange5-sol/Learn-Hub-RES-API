package com.example.LearnHub.dto;

import com.example.LearnHub.model.Comment;
import com.example.LearnHub.model.Enrollment;
import com.example.LearnHub.model.Lesson;
import java.time.LocalDateTime;
import java.util.List;

public class CourseResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long userId;
    private List<Lesson> lessons;
    private List<Comment> comments;
    private List<Enrollment> enrollments;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public List<Enrollment> getEnrollments() { return enrollments; }
    public void setEnrollments(List<Enrollment> enrollments) { this.enrollments = enrollments; }

}
