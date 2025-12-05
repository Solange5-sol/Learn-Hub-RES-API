package com.example.LearnHub.repository;

import com.example.LearnHub.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUserId(Long userId);
    List<Course> findByNameContaining(String name);

}
