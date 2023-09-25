package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.response.DataListResponse;
import com.alura.latam.forum.domain.response.Response;
import com.alura.latam.forum.domain.response.ResponseRepository;
import com.alura.latam.forum.domain.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topics")
//@SecurityRequirement(name = "bearer-key")
public class TopicController {

    @Autowired
    private TopicService service;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerTopic(@RequestBody @Valid DataRegisterTopic data) {
        var response = service.registerTopic(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> listTopics(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var response = service.listTopics(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retornoTopicoRespuestas(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable, @PathVariable Long id){
        var response = service.detailTopic(pageable, id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid DataUpdateTopic data) {
        var response = service.updateTopic(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        service.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
