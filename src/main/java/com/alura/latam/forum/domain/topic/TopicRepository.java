package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.response.Response;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Boolean existsByTitleAndMessage(String title, String message);

    Page<Topic> findByActiveTrue(Pageable pageable);

}