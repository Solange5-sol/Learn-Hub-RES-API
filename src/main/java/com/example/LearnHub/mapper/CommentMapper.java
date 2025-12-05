package com.example.LearnHub.mapper;

import com.example.LearnHub.dto.CommentCreateDTO;
import com.example.LearnHub.dto.CommentResponseDTO;
import com.example.LearnHub.model.Comment;
import com.example.LearnHub.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CommentCreateDTO commentCreateDTO, Course course) {
        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        return comment;
    }
    public CommentResponseDTO toDto(Comment comment) {
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setContent(comment.getContent());
        commentResponseDTO.setCreatedAt(comment.getCreatedAt());
        commentResponseDTO.setCourseId(comment.getCourse().getId());
        commentResponseDTO.setUserId(comment.getUser().getId());
        return commentResponseDTO;

    }


}
