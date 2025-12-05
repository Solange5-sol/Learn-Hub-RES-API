package com.example.LearnHub.controller;

import com.example.LearnHub.dto.LoginRequestDTO;
import com.example.LearnHub.dto.LoginResponseDTO;
import com.example.LearnHub.dto.RegisterRequestDTO;
import com.example.LearnHub.model.Role;
import com.example.LearnHub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService authService;
    public UserController(UserService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register (@Valid @RequestBody RegisterRequestDTO dto) {
        authService.register(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("registration success");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequestDTO dto) {
        LoginResponseDTO responseDTO = authService.login(dto);
        return ResponseEntity.ok(responseDTO);
    }
}
