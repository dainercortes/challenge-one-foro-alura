package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.course.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registra un nuevo curso, solo lo puede hacer el administrador",
            tags = {"course", "post"}
    )
    public ResponseEntity registerCourse(@RequestBody @Valid DataRegisterCourse data) {
        var response = service.registerCourse(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Acttualiza los datos de un curso, solo lo puede hacer el administrador",
            tags = {"course", "put"}
    )
    public ResponseEntity updateCourse(@RequestBody @Valid DataUpdateCourse data) {
        var course = service.updateCourse(data);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "elimina un curso curso, solo lo puede hacer el administrador",
            tags = {"course", "delete"}
    )
    public ResponseEntity deleteCourse(@PathVariable @Min(1) Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
