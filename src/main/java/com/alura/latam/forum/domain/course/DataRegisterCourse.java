package com.alura.latam.forum.domain.course;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCourse(
        @NotBlank
        String name,
        @NotBlank
        String category) {
}
