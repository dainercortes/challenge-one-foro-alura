package com.alura.latam.forum.domain.user;

import com.alura.latam.forum.domain.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
}
