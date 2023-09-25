package com.alura.latam.forum.domain.response;

import com.alura.latam.forum.domain.topic.Topic;
import com.alura.latam.forum.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "responses")
@Entity(name = "Response")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topic")
    private Topic topic;

    LocalDateTime creation_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private User author;

    private Boolean solution; // false
    private Boolean active;

    public Response(String message, Topic topic, LocalDateTime creation_date, User author) {
        this.message = message;
        this.topic = topic;
        this.creation_date = creation_date;
        this.author = author;
        this.solution = false;
        this.active = true;
    }

    public void updateResponse(String message) {
        if (message != null) {
            this.message = message;
        }
    }

    public void deleteResponse() {
        this.active = false;
    }
}
