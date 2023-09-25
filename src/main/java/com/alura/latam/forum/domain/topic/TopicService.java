package com.alura.latam.forum.domain.topic;

import com.alura.latam.forum.domain.course.CourseRepository;
import com.alura.latam.forum.domain.response.DataListResponse;
import com.alura.latam.forum.domain.response.Response;
import com.alura.latam.forum.domain.response.ResponseRepository;
import com.alura.latam.forum.domain.user.UserRepository;
import com.alura.latam.forum.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public DataResponseTopic registerTopic(DataRegisterTopic data) {

        if (!userRepository.findById(data.id_author()).isPresent()) {
            throw new IntegrityValidation("El usuario no esta registrado en el sistema");
        }

        if (!coursesRepository.findById(data.id_course()).isPresent()) {
            throw new IntegrityValidation("El curso no esta registrado en el sistema");
        }

        var author = userRepository.findById(data.id_author()).get();
        var course = coursesRepository.findById(data.id_course()).get();

        var topic = new Topic(data.title(), data.message(), LocalDateTime.now(), author, course);
        topicRepository.save(topic);

        return new DataResponseTopic(topic);
    }


    public Page<DataListTopic> listTopics(Pageable pageable) {
        var page = topicRepository.findAll(pageable).map(DataListTopic::new);

      return page;
    }

    public Object detailTopic(Pageable pageable, Long id) {
        Topic topic = topicRepository.getReferenceById(id);

        if (topic != null)  {
            Page<Response> responses = responseRepository.findByTopicId(pageable, id);

            if (!responses.isEmpty()) {
                Page<DataListResponse> responsePage = responses.map(response -> new DataListResponse(
                        response.getId(),
                        response.getMessage(),
                        response.getCreation_date(),
                        response.getAuthor().getName(),
                        response.getSolution()) );

                return new DataDetailTopic(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getMessage(),
                        topic.getCreation_date(),
                        topic.getStatus(),
                        topic.getAuthor().getName(),
                        topic.getCourse().getName(),
                        responsePage.getContent());

            } else {
                return new DataListTopic(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getMessage(),
                        topic.getCreation_date(),
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
        var topic = topicRepository.getReferenceById(data.id());
        var course = coursesRepository.findById(data.id_course()).get();

        topic.updateTopic(data.title(), data.message(), data.status(), course);
        return new DataResponseTopic(topic);
    }

    public void deleteTopic(Long id) {
        var topic = topicRepository.getReferenceById(id);
        topic.deleteTopic();
    }
}
