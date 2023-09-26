package com.alura.latam.forum.infra.errors;

public class isNegativeId {

    public static void verifier(Long id) {
        if (id <= 0) {
            throw new IntegrityValidation("El id no puedo ser menor o igual que 0");
        }
    }
}
