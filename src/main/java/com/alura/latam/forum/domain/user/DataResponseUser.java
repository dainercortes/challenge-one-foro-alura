package com.alura.latam.forum.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DataResponseUser(Long id, String name, String email, String username) {

    public DataResponseUser(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getUsername());
    }
}
