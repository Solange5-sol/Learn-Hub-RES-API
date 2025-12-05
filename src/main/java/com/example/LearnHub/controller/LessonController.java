package com.example.LearnHub.controller;

import com.example.LearnHub.dto.LessonCreateDTO;
import com.example.LearnHub.dto.LessonResponseDTO;
import com.example.LearnHub.model.User;
import com.example.LearnHub.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<?> createLesson(
            @PathVariable Long courseId,
            @Valid @RequestBody LessonCreateDTO less,
            @AuthenticationPrincipal User user)
    {
        LessonResponseDTO createdLesson = lessonService.createLesson(courseId, less, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getAllLessons(@PathVariable Long courseId){
        return ResponseEntity.ok(lessonService.getAllLessons(courseId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable Long id){
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(
            @PathVariable Long id,
            @Valid @RequestBody LessonCreateDTO dto,
            @AuthenticationPrincipal User user
    ){
        LessonResponseDTO res=lessonService.updateLesson(id , dto, user);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLessonById(@PathVariable Long id, @AuthenticationPrincipal User user){
        lessonService.deleteLesson(id,user);
        return ResponseEntity.ok("deleted");

    }

}
