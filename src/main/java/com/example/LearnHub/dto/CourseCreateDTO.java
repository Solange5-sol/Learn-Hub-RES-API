package com.example.LearnHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseCreateDTO {

    @NotBlank(message = "name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "description is required")
    @Size(min = 10, max = 600)
    private String description;
    private Long userId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

}
