package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.course.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
//@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterCourse data) {
        var response = service.registerCourse(data);
        return ResponseEntity.ok(response);
    }
}
