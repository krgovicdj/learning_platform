package org.acme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
@NamedQuery(name=Course.GET_ALL_COURSES, query = "Select c from Course c")
@Entity
public class Course {
    public static final String GET_ALL_COURSES = "GetAllCourses";
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    @Id
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> students;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lesson> lessons;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Category> categories;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Course course)) return false;
        return Objects.equals(getId(), course.getId()) && Objects.equals(getName(), course.getName()) && Objects.equals(getInstructor(), course.getInstructor()) && Objects.equals(getStudents(), course.getStudents()) && Objects.equals(getLessons(), course.getLessons()) && Objects.equals(getCategories(), course.getCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getInstructor(), getStudents(), getLessons(), getCategories());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
