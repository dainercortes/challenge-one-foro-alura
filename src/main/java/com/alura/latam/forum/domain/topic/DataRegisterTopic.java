package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.course.Course;
import com.alura.latam.forum.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long idAuthor,
        @NotNull
        Long idCourse) {
}
