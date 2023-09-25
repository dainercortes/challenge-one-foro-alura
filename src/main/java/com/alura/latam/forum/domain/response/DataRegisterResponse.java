package com.alura.latam.forum.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterResponse(
        @NotBlank
        String message,
        @NotNull
        Long id_topic,
        @NotNull
        Long id_author
) {
}
