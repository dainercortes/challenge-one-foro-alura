package com.alura.latam.forum.domain.course;

import com.alura.latam.forum.domain.response.DataResponse;
import com.alura.latam.forum.domain.response.DataUpdateResponse;
import com.alura.latam.forum.domain.user.UserActiveSesion;
import com.alura.latam.forum.infra.errors.IntegrityValidation;
import com.alura.latam.forum.infra.errors.isNegativeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Value("${forum.usur.username}")
    private String userAdmin;

    public DataResponseCourse registerCourse(DataRegisterCourse data) {
        var course = new Course(data.name(), data.category());
        courseRepository.save(course);
        return new DataResponseCourse(course);
    }

    public DataResponseCourse updateCourse(DataUpdateCourse data) {
        validateUserActiveSesion();
        isNegativeId.verifier(data.id());
        validateCourseExistsId(data.id());
        validateCourseExistsName(data.name());

        var course = courseRepository.getReferenceById(data.id());
        course.updateCourse(data.name(), data.category());
        return new DataResponseCourse(course);
    }

    public void deleteCourse(Long id) {
        validateUserActiveSesion();
        isNegativeId.verifier(id);
        validateCourseExistsId(id);

        var course = courseRepository.getReferenceById(id);
        course.deleteCourse();
    }

    //Validations
    private void validateUserActiveSesion() {
        if (!UserActiveSesion.username.equals(userAdmin)) {
            throw new IntegrityValidation("El unico que puede eliminar cursos es el adminstrador");
        }
    }

    private void validateCourseExistsId(Long id) {
        if (!courseRepository.findById(id).isPresent()) {
            throw new IntegrityValidation("El curso no se encontro en la base de datos");
        }
    }

    private void validateCourseExistsName(String name) {
        if (courseRepository.findByName(name)) {
            throw new IntegrityValidation("El curso ya fue registrado anteriormente");
        }
    }
}
