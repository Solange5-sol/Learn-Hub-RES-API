package com.example.LearnHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LessonCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank
    @Size(min = 2, max = 100)
    private String content;
    private Integer courseId;

    public String getTitle() {return  title;}
    public void setTitle(String title) {this.title = title;}
    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
    public Integer getCourseId() {return courseId;}
    public void setCourseId(Integer courseId) {this.courseId = courseId;}
}
