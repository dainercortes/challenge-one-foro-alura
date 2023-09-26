package com.alura.latam.forum.domain.user;

import com.alura.latam.forum.infra.errors.IntegrityValidation;
import com.alura.latam.forum.infra.errors.isNegativeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${forum.usur.username}")
    private String userAdmin;

    public DataResponseUser registerUser(DataRegisterUser data) {
        validateEmail(data.email());
        validationUsername(data.username());

        var user = new User(data.name(), data.email(), data.username(), data.password());

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return new DataResponseUser(user);
    }

    public DataResponseUser updateUser(DataUpdateUser data) {
        isNegativeId.verifier(data.id());
        validateUser(data.id());
        validateEmail(data.email());
        validationUsername(data.username());


        var user = userRepository.getReferenceById(data.id());
        user.updateUser(data.name(), data.email(), data.username(), data.password());

        return new DataResponseUser(user);
    }

    public void deleteUser(Long id) {
        isNegativeId.verifier(id);
       validateUser(id);

        var user = userRepository.getReferenceById(id);
        user.deleteUser();
    }

    //Validations
    private void validateUser(Long id) {
        if (!id.equals(UserActiveSesion.idUser) || !UserActiveSesion.username.equals(userAdmin) ) {
            throw new IntegrityValidation("El usuario que intenta modificar no es el mismo que inicio sesion");
        }
    }

    private void validateEmail(String email) {
        if (email != null && userRepository.existsByEmail(email)) {
            throw new IntegrityValidation("El email ya fue registrado anteriormente");
        }
    }

    private void validationUsername(String username) {
        if (username != null && userRepository.existsByUsername(username)) {
            throw new IntegrityValidation("El nombre de usuario ya fue registrado anteriormente");
        }
    }

}
