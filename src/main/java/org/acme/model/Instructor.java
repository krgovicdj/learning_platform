package org.acme.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Instructor {

    @Id
    private Long id;
    private String name;
    private String email;
    @OneToOne
    private Profile profile;
    @OneToMany
    private List<Course> courses;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}