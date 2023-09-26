package com.alura.latam.forum.domain.response;

import com.alura.latam.forum.domain.topic.Topic;
import com.alura.latam.forum.domain.user.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataResponse(
        Long id,
        String message,
        String topic,
        LocalDateTime creationDate,
        String author,
        Boolean solution) {

    public DataResponse(Response response) {
        this(response.getId(), response.getMessage(), response.getTopic().getTitle(), response.getCreationDate(), response.getAuthor().getName(), response.getSolution());
    }
}
