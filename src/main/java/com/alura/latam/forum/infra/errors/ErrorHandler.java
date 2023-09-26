package com.alura.latam.forum.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity dealError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity dealError400(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("El formato que esta enviando no es el correcto");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity dealError400(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity errorHandlerIntregityValidation(IntegrityValidation e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity dealError400(MethodArgumentTypeMismatchException e) {
        return  ResponseEntity.badRequest().body("Verifique que este enviando el id como valor numerico");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity dealError400(NullPointerException e) {
        return ResponseEntity.badRequest().body("Verifique que este enviado el Token o los demas datos solicitados");
    }


    private record DataErrorValidation(String field, String error) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
