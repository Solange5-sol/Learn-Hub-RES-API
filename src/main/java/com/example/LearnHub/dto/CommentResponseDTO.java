package com.example.LearnHub.dto;

import java.time.LocalDateTime;
public class CommentResponseDTO {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
    private Long courseId;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

}
