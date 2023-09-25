package com.alura.latam.forum.infra.errors;

public class IntegrityValidation extends RuntimeException {

    public IntegrityValidation(String s) { super(s); }
}
