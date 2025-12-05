package com.example.LearnHub.service;

import com.example.LearnHub.dto.LoginRequestDTO;
import com.example.LearnHub.dto.LoginResponseDTO;
import com.example.LearnHub.dto.RegisterRequestDTO;
import com.example.LearnHub.exception.InvalidTokenException;
import com.example.LearnHub.exception.UserAlreadyExistsException;
import com.example.LearnHub.model.Role;
import com.example.LearnHub.model.User;
import com.example.LearnHub.repository.UserRepository;
import com.example.LearnHub.security.JwtUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, JwtUtils jwtUtils, BCryptPasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(RegisterRequestDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setHashedPassword(passwordEncoder.encode(dto.getPassword()));

        if (dto.getRole() == null || dto.getRole() == Role.USER) {
            user.setRole(Role.USER);
        } else if (dto.getRole() == Role.INSTRUCTOR) {
            user.setRole(Role.INSTRUCTOR);
        } else {
            throw new IllegalArgumentException("Cannot register as ADMIN");
        }

        userRepository.save(user);
    }
    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getHashedPassword())) {
            throw new InvalidTokenException("Invalid email or password");
        }
        String token = jwtUtils.generateToken(
                user.getEmail(),
                Map.of(
                        "userId", user.getId(),
                        "role", user.getRole().name()
                )
        );
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUsername(user.getUsername());
        return response;
    }
    @PostConstruct
    public void admin() {
        if (!userRepository.existsByEmail("admin@example.com")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setHashedPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
