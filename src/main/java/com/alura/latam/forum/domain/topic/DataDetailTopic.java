package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.response.DataListResponse;

import java.time.LocalDateTime;
import java.util.List;

public record DataDetailTopic(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        StatusTopic status,
        String author,
        String course,
        List<DataListResponse> response
) {
}
