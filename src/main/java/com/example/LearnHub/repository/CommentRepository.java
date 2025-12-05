package com.example.LearnHub.repository;

import com.example.LearnHub.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCourseId(Long courseId);
    List<Comment> findByUserId(Long userId);
}
