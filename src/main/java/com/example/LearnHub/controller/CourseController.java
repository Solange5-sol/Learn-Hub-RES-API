package com.example.LearnHub.controller;

import com.example.LearnHub.dto.CourseCreateDTO;

import com.example.LearnHub.model.User;
import com.example.LearnHub.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseCreateDTO createDTO, @AuthenticationPrincipal User user){
        if(user==null){
            throw new IllegalArgumentException("Invalid token");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(createDTO,user));
    }
    @GetMapping
    public ResponseEntity<?> getCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseCreateDTO dto,
            @AuthenticationPrincipal User user){

        return ResponseEntity.ok(courseService.updateCourse(id,dto,user));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id, @AuthenticationPrincipal User user){
        return ResponseEntity.ok(courseService.deleteCourse(id,user));
    }
}
