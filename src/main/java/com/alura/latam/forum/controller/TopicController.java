package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.response.ResponseRepository;
import com.alura.latam.forum.domain.topic.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicService service;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registra un nuevo topico",
            tags = {"topic", "post"}
    )
    public ResponseEntity registerTopic(@RequestBody @Valid DataRegisterTopic data) {
        var response = service.registerTopic(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Muestra todos los topicos activos",
            tags = {"topic", "get"}
    )
    public ResponseEntity<Page<DataListTopic>> listTopics(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var response = service.listTopics(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Muestra el topico y sus respuestas asociadas",
            tags = {"topic", "get"}
    )
    public ResponseEntity<Object> retornoTopicoRespuestas(@PathVariable @Min(1) Long id){
        var response = service.detailTopic(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualiza los datos del topico",
            tags = {"topic", "put"}
    )
    public ResponseEntity updateTopic(@RequestBody @Valid DataUpdateTopic data) {
        var response = service.updateTopic(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Elimina un topico",
            tags = {"topic", "delete"}
    )
    public ResponseEntity deleteTopic(@PathVariable @Min(1) Long id) {
        service.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
