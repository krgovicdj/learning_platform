package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@NamedQuery(name = Student.GET_ALL_STUDENTS, query = "Select s from Student s")
@NamedQuery(name = Student.GET_STUDENT_BY_NAME, query = "SELECT s FROM Student s WHERE s.name = :name")
public class Student {

    public static final String GET_ALL_STUDENTS = "GetAllStudents";
    public static final String GET_STUDENT_BY_NAME = "GetStudentByName";
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    @Id
    private Long id;
    private String name;
    private String lastname;
    @OneToOne
    private Profile profile;
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Course> courses;

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return Objects.equals(getId(), student.getId()) && Objects.equals(getName(), student.getName()) && Objects.equals(getLastname(), student.getLastname()) && Objects.equals(getProfile(), student.getProfile()) && Objects.equals(getCourses(), student.getCourses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastname(), getProfile(), getCourses());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<Course> getCourses() {
        return courses;
    }
}