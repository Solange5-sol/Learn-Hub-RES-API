package com.example.LearnHub.controller;

import com.example.LearnHub.dto.CommentCreateDTO;
import com.example.LearnHub.dto.CommentResponseDTO;
import com.example.LearnHub.model.User;
import com.example.LearnHub.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable Long courseId,
            @RequestBody CommentCreateDTO dto,
            @AuthenticationPrincipal User user) {
        CommentResponseDTO response = commentService.createComment(courseId, dto, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByCourse(
            @PathVariable Long courseId) {
        List<CommentResponseDTO> comments = commentService.getCommentsByCourse(courseId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal User user) {
        CommentResponseDTO deleted = commentService.deleteComment(commentId, user);
        return ResponseEntity.ok(deleted);
    }
}
