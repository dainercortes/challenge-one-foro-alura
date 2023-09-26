package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.course.CourseRepository;
import com.alura.latam.forum.domain.response.DataListResponse;
import com.alura.latam.forum.domain.response.Response;
import com.alura.latam.forum.domain.response.ResponseRepository;
import com.alura.latam.forum.domain.user.UserActiveSesion;
import com.alura.latam.forum.domain.user.UserRepository;
import com.alura.latam.forum.infra.errors.IntegrityValidation;
import com.alura.latam.forum.infra.errors.isNegativeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository coursesRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ResponseRepository responseRepository;
    @Value("${forum.usur.username}")
    private String userAdmin;

    public DataResponseTopic registerTopic(DataRegisterTopic data) {
        isNegativeId.verifier(data.idAuthor());
        isNegativeId.verifier(data.idCourse());
        validateUserExists(data.idAuthor());
        validateUser(data.idAuthor());
        validateCourseExists(data.idCourse());

        var author = userRepository.findById(data.idAuthor()).get();
        var course = coursesRepository.findById(data.idCourse()).get();

        topicRepository.existsByTitleAndMessage(data.title(), data.message());

        var topic = new Topic(data.title(), data.message(), LocalDateTime.now(), author, course);
        topicRepository.save(topic);

        return new DataResponseTopic(topic);
    }


    public Page<DataListTopic> listTopics(Pageable pageable) {
        var page = topicRepository.findByActiveTrue(pageable).map(DataListTopic::new);

      return page;
    }

    public Object detailTopic(Long id) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").ascending());

        isNegativeId.verifier(id);
        validateTopicExists(id);

        Topic topic = topicRepository.getReferenceById(id);

        validateTopicActive(topic.getActive());

        if (topic != null)  {
            Page<Response> responses = responseRepository.findByTopicIdAndActiveTrue(pageable, id);

            if (!responses.isEmpty()) {
                Page<DataListResponse> responsePage = responses.map(response -> new DataListResponse(
                        response.getId(),
                        response.getMessage(),
                        response.getCreationDate(),
                        response.getAuthor().getName(),
                        response.getSolution()) );

                return new DataDetailTopic(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getMessage(),
                        topic.getCreationDate(),
                        topic.getStatus(),
                        topic.getAuthor().getName(),
                        topic.getCourse().getName(),
                        responsePage.getContent());

            } else {
                return new DataListTopic(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getMessage(),
                        topic.getCreationDate(),
                        topic.getStatus(),
                        topic.getAuthor().getName(),
                        topic.getCourse().getName()
                );
            }
        } else {
            return Page.empty();
        }
    }

    public DataResponseTopic updateTopic(DataUpdateTopic data) {
        isNegativeId.verifier(data.id());
        isNegativeId.verifier(data.idCourse());
        validateTopicExists(data.id());
        validateCourseExists(data.idCourse());

        var topic = topicRepository.getReferenceById(data.id());
        var course = coursesRepository.findById(data.idCourse()).get();

        validateUserActiveSesion(topic.getAuthor().getId());

        topic.updateTopic(data.title(), data.message(), data.status(), course);
        return new DataResponseTopic(topic);
    }

    public void deleteTopic(Long id) {
        isNegativeId.verifier(id);
        validateTopicExists(id);

        var topic = topicRepository.getReferenceById(id);

        validateUserActiveSesion(topic.getAuthor().getId());

        topic.deleteTopic();
    }

    //Validations
    private void validateUserExists(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            throw new IntegrityValidation("El usuario no esta registrado en el sistema");
        }
    }

    private void validateUser(Long id) {
        if (!id.equals(UserActiveSesion.idUser)) {
            throw new IntegrityValidation("El usuario que intenta crear el topico es diferente al usuario que inicio sesion");
        }
    }

    private void validateCourseExists(Long id) {
        if (!coursesRepository.findById(id).isPresent()) {
            throw new IntegrityValidation("El curso no esta registrado en el sistema");
        }
    }

    private void validateTopicExists(Long id) {
        if (!topicRepository.findById(id).isPresent()) {
            throw new IntegrityValidation("El topico no se encontro en la base de datos");
        }
    }

    private void validateTopicActive(Boolean active) {
        if (active == false) {
            throw new IntegrityValidation("El topico no esta disponible fue eliminado anteriormente");
        }
    }

    private  void validateTitleAndMessageExists(String title, String message) {
        if (!topicRepository.existsByTitleAndMessage(title, message)) {
            throw new IntegrityValidation("El titulo y el mensaje son iguales a otro topico de la base de datos");
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
