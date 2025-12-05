package com.example.LearnHub.dto;

public class EnrollmentCreateDTO {

    private Long userId;
    private Long courseId;

    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}

    public Long getCourseId() {return courseId;}
    public void setCourseId(Long courseId) {this.courseId = courseId;}

}
