package com.alura.latam.forum.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateResponse(
        @NotNull
        Long id,
        String message
) {

}
