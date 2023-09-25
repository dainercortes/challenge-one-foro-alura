package com.alura.latam.forum.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DataRegisterUser(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password) {
}
