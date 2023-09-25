package com.alura.latam.forum.domain.course;

public record DataResponseCourse(Long id, String name, String category) {

    public DataResponseCourse(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
