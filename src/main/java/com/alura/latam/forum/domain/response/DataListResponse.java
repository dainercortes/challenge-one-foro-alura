package com.alura.latam.forum.domain.response;

import java.time.LocalDateTime;

public record DataListResponse(
        Long id,
        String message,
        LocalDateTime creation_date,
        String author,
        Boolean solution) {

    public DataListResponse(Response response) {
        this(response.getId(), response.getMessage(), response.getCreation_date(), response.getAuthor().getName(), response.getSolution());
    }
}
