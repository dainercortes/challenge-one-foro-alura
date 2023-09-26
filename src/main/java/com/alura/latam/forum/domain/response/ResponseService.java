package com.alura.latam.forum.domain.response;

import com.alura.latam.forum.domain.topic.TopicRepository;
import com.alura.latam.forum.domain.user.UserActiveSesion;
import com.alura.latam.forum.domain.user.UserRepository;
import com.alura.latam.forum.infra.errors.IntegrityValidation;
import com.alura.latam.forum.infra.errors.isNegativeId;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResponseService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ResponseRepository responseRepository;
    @Value("${forum.usur.username}")
    private String userAdmin;

    public DataResponse registerResponse(DataRegisterResponse data) {
        isNegativeId.verifier(data.idTopic());
        isNegativeId.verifier(data.idAuthor());
        validateTopicExists(data.idTopic());
        validateUserExists(data.idAuthor());
        valideUser(data.idAuthor());

        var topic = topicRepository.findById(data.idTopic()).get();
        var author = userRepository.findById(data.idAuthor()).get();

        var response = new Response(data.message(),topic, LocalDateTime.now(), author);
        responseRepository.save(response);

        return new DataResponse(response);
    }

    public DataResponse updateResponse(DataUpdateResponse data) {
        isNegativeId.verifier(data.id());
        validateResponseExists(data.id());

        var response = responseRepository.getReferenceById(data.id());

        validateUserActiveSesion(response.getAuthor().getId());

        response.updateResponse(data.message());
        return new DataResponse(response);
    }

    public void deleteResponse(Long id) {
        isNegativeId.verifier(id);
        validateResponseExists(id);

        var response = responseRepository.getReferenceById(id);

        validateUserActiveSesion(response.getAuthor().getId());

        response.deleteResponse();
    }

    //Validations
    private void validateTopicExists(Long id) {
        if (!topicRepository.findById(id).isPresent()) {
            throw new IntegrityValidation("El topico al que intenta responder no existe en la base de datos");
        }
    }

    private void validateUserExists(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new IntegrityValidation("El usuario no esta registrado en el sistema");
        }
    }

    private void valideUser(Long id) {
        if (!id.equals(UserActiveSesion.idUser)) {
            throw new IntegrityValidation("El usuario que intenta responder no es el mismo que inicio sesion");
        }
    }

    private void validateResponseExists(Long id) {
        if (!responseRepository.findById(id).isPresent()) {
            throw new IntegrityValidation("La respuesta no se encontro en la base de datos");
        }
    }

    private void validateUserActiveSesion(Long id) {
        if (!id.equals(UserActiveSesion.idUser)) {
            throw new IntegrityValidation("El usuario que intenta realizar la operacion no es el mismo que inicio sesion");
        }

        if (!id.equals(UserActiveSesion.idUser) && !UserActiveSesion.username.equals(userAdmin)) {
            throw new IntegrityValidation("El usuario que intenta realizar la operacion no es el mismo que inicio sesion");
        }
    }
}
