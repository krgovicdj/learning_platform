package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Lesson {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    @Id
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Course course;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lesson lesson)) return false;
        return Objects.equals(getId(), lesson.getId()) && Objects.equals(getTitle(), lesson.getTitle()) && Objects.equals(getCourse(), lesson.getCourse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCourse());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}