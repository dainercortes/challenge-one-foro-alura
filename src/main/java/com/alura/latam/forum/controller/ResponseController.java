package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.response.DataRegisterResponse;
import com.alura.latam.forum.domain.response.DataUpdateResponse;
import com.alura.latam.forum.domain.response.ResponseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    private ResponseService service;

    @PostMapping
    @Transactional
    public ResponseEntity registerResponse(@RequestBody @Valid DataRegisterResponse data) {
        var response = service.registerResponse(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateResponse(@RequestBody @Valid DataUpdateResponse data) {
        var response = service.updateResponse(data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        service.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}
