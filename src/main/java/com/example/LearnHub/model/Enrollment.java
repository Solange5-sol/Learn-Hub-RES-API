package com.example.LearnHub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch =FetchType.LAZY )
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    public Enrollment() {}
    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public Course getCourse() {return course;}
    public void setCourse(Course course) {this.course = course;}
}
