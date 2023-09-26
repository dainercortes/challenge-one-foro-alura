package com.alura.latam.forum.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
        @NotNull
        Long id,
        String title,
        String message,
        StatusTopic status,
        Long idCourse
) {
}
