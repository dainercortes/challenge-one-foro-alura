package com.alura.latam.forum.domain.response;

import com.alura.latam.forum.domain.topic.TopicRepository;
import com.alura.latam.forum.domain.user.UserRepository;
import com.alura.latam.forum.infra.errors.IntegrityValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public DataResponse registerResponse(DataRegisterResponse data) {

        if (!topicRepository.findById(data.id_topic()).isPresent()) {
            throw new IntegrityValidation("El topico al que intenta responder no existe en la base de datos");
        }
        if (!userRepository.findById(data.id_author()).isPresent()) {
            throw new IntegrityValidation("El usuario no esta registrado en el sistema");
        }
        var topic = topicRepository.findById(data.id_topic()).get();
        var author = userRepository.findById(data.id_author()).get();

        var response = new Response(data.message(),topic, LocalDateTime.now(), author);
        responseRepository.save(response);

        return new DataResponse(response);

    }



    public DataResponse updateResponse(DataUpdateResponse data) {
        var response = responseRepository.getReferenceById(data.id());

        response.updateResponse(data.message());
        return new DataResponse(response);
    }

    public void deleteResponse(Long id) {
        var response = responseRepository.getReferenceById(id);
        response.deleteResponse();
    }
}
