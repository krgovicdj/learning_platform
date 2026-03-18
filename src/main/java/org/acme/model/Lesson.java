package org.acme.model;

import jakarta.persistence.*;

@Entity
public class Lesson {
    @Id
    private Long id;
    private String title;
    @ManyToOne
    private Course course;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Course getCourse() {
        return course;
    }
}