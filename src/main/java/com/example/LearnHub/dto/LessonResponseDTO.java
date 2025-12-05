package com.example.LearnHub.dto;
import java.time.LocalDateTime;

public class LessonResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long courseId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return  title;}
    public void setTitle(String title) {this.title = title;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public  Long getCourseId() {return courseId;}
    public void setCourseId(Long courseId) {this.courseId = courseId;}

}
