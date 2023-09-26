package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.course.Course;
import com.alura.latam.forum.domain.response.Response;
import com.alura.latam.forum.domain.user.User;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public record DataListTopic(Long id,
                            String title,
                            String message,
                            LocalDateTime creationDate,
                            StatusTopic status,
                            String author,
                            String course) {

    public DataListTopic(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreationDate(),
                topic.getStatus(), topic.getAuthor().getName(), topic.getCourse().getName());
    }

}
