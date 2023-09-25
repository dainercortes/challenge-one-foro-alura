package com.alura.latam.forum.domain.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public DataResponseCourse registerCourse(DataRegisterCourse data) {

        var course = new Course(data.name(), data.category());

        courseRepository.save(course);

        return new DataResponseCourse(course);
    }
}
