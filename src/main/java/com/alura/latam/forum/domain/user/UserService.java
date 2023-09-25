package com.alura.latam.forum.domain.user;

import com.alura.latam.forum.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public DataResponseUser registerUser(DataRegisterUser data) {

        if (data.email() != null && userRepository.existsByEmail(data.email())) {
            throw new IntegrityValidation("El email ya fue registrado anteriormente");
        }

        var user = new User(data.name(), data.email(), data.password());

        userRepository.save(user);

        return new DataResponseUser(user);
    }

}
