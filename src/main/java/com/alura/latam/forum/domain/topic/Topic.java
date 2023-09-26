package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.course.Course;
import com.alura.latam.forum.domain.response.Response;
import com.alura.latam.forum.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTopic status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course")
    private Course course;

    private Boolean active;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Response> responses = new ArrayList<>();


    public Topic(String title, String message, LocalDateTime creationDate, User author, Course course) {
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.status = StatusTopic.NO_RESPONDIDO;
        this.author = author;
        this.course = course;
        this.active = true;
    }

    public void updateTopic(String title, String message, StatusTopic status, Course course) {

        if (title != null) {
            this.title = title;
        }
        if (message != null) {
            this.message = message;
        }
        if(status != null) {
            this.status = status;
        }
        if(course != null) {
            this.course = course;
        }
    }

    public void deleteTopic() {
        this.active = false;
    }
}
