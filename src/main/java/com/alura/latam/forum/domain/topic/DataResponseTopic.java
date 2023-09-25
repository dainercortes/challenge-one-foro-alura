package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.course.Course;
import com.alura.latam.forum.domain.response.Response;
import com.alura.latam.forum.domain.user.User;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public record DataResponseTopic(Long id,
                                String title,
                                String message,
                                LocalDateTime creation_date,
                                StatusTopic status,
                                Long id_author,
                                Long id_course) {
    public DataResponseTopic(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreation_date(), topic.getStatus(), topic.getAuthor().getId(), topic.getCourse().getId());
    }
}
