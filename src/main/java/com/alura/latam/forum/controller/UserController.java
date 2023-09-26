package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Registro de nuevo usuario",
            tags = {"sign out", "user", "post"}
    )
    public ResponseEntity registerUser(@RequestBody @Valid DataRegisterUser data) {
        var response = service.registerUser(data);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Actualiza datos de usuario",
            tags = {"user", "put"}
    )
    public ResponseEntity updateUser(@RequestBody @Valid DataUpdateUser data) {
        var user = service.updateUser(data);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Elimina el usuario",
            tags = {"user", "delete"}
    )
    public ResponseEntity deleteUser(@PathVariable @Min(1) Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
