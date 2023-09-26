package com.alura.latam.forum.controller;

import com.alura.latam.forum.domain.user.DataAutenticationUser;
import com.alura.latam.forum.domain.user.User;
import com.alura.latam.forum.infra.security.DataJWTToken;
import com.alura.latam.forum.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "authentication", description = "Obtiene el Token para el usuario")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticationUser(@RequestBody @Valid DataAutenticationUser data) {
        Authentication autoToken = new UsernamePasswordAuthenticationToken(data.username(),
                data.password());
        var autenticatedUser = authenticationManager.authenticate(autoToken);
        var JWTtoken = tokenService.generateToken((User) autenticatedUser.getPrincipal());

        return ResponseEntity.ok(new DataJWTToken(JWTtoken));
    }
}
