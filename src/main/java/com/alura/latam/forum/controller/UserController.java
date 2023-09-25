package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.user.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterUser data) {
        var response = service.registerUser(data);
        return ResponseEntity.ok(response);
    }
}
