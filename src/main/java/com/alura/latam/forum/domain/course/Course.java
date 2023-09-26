package com.alura.latam.forum.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Boolean active;

    public Course(String name, String category) {
        this.name = name;
        this.category = category;
        this.active = true;
    }

    public void deleteCourse() {
        this.active = false;
    }

    public void updateCourse(String name, String category) {
        if (name != null) {
            this.name = name;
        }

        if (category != null) {
            this.category = category;
        }
    }
}
