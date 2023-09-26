package com.alura.latam.forum.domain.course;

import jakarta.validation.constraints.NotNull;

public record DataUpdateCourse(
        @NotNull
        Long id,
        String name,
        String category
) {
}
