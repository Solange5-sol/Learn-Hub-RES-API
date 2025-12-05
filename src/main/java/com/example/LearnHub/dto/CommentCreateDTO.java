package com.example.LearnHub.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentCreateDTO {
    @NotBlank(message = "Comment content is required")
    private String content;

    private Long courseId;
    private Long userId;

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public Long getCourseId() {return courseId;}
    public void setCourseId(Long courseId) {this.courseId = courseId;}
    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
}
