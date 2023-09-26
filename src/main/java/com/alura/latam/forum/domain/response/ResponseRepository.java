package com.alura.latam.forum.domain.response;

import com.alura.latam.forum.domain.topic.Topic;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    Page<Response> findByTopicId(Pageable pageable, Long id);

    Page<Response> findByTopicIdAndActiveTrue(Pageable pageable, Long id);
}
