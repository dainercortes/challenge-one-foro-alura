package com.alura.latam.forum.domain.response;

import java.time.LocalDateTime;

public record DataListResponse(
        Long id,
        String message,
        LocalDateTime creationDate,
        String author,
        Boolean solution) {

    public DataListResponse(Response response) {
        this(response.getId(), response.getMessage(), response.getCreationDate(), response.getAuthor().getName(), response.getSolution());
    }
}
