package com.example.LearnHub.repository;

import com.example.LearnHub.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);
    List<Enrollment>findByUserId(Long userId);
    List<Enrollment> findByCourseId(Long courseId);
    int countByCourseId(Long courseId);

}
