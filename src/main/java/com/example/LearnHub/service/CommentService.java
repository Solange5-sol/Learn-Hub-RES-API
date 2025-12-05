package com.example.LearnHub.service;

import com.example.LearnHub.dto.CommentCreateDTO;
import com.example.LearnHub.dto.CommentResponseDTO;
import com.example.LearnHub.exception.CommentNotFoundException;
import com.example.LearnHub.exception.CourseNotFoundException;
import com.example.LearnHub.mapper.CommentMapper;
import com.example.LearnHub.model.Comment;
import com.example.LearnHub.model.Course;
import com.example.LearnHub.model.User;
import com.example.LearnHub.repository.CommentRepository;
import com.example.LearnHub.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CourseRepository courseRepository;

    public CommentService(CommentRepository commentRepository,
                          CommentMapper commentMapper,
                          CourseRepository courseRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.courseRepository = courseRepository;
    }
    public CommentResponseDTO createComment(Long courseId, CommentCreateDTO dto, User user) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));
        Comment comment = commentMapper.toEntity(dto, course);
        comment.setUser(user);
        comment.setCourse(course);
        Comment saved = commentRepository.save(comment);
        return commentMapper.toDto(saved);
    }
    public List<CommentResponseDTO> getCommentsByCourse(Long courseId) {
        return commentRepository.findByCourseId(courseId)
                .stream()
                .map(commentMapper::toDto)
                .toList();
    }
    public CommentResponseDTO updateComment(Long commentId, CommentCreateDTO dto, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        if(!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("You are not the owner of this comment");
        }

        comment.setContent(dto.getContent());
        Comment updated = commentRepository.save(comment);
        return commentMapper.toDto(updated);
    }
    public CommentResponseDTO deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + commentId));

        if (!comment.getUser().getId().equals(user.getId()))
        {
            throw new IllegalArgumentException("You do not have permission to delete this comment");
        }

        commentRepository.delete(comment);
        return commentMapper.toDto(comment);
    }

}
