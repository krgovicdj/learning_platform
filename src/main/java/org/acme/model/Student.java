package org.acme.model;

import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Example JPA entity.
 *
 * To use it, get access to a JPA EntityManager via injection.
 *
 * {@code
 *     @Inject
 *     EntityManager em;
 *
 *     public void doSomething() {
 *         org.acme.model.MyEntity entity1 = new org.acme.model.MyEntity();
 *         entity1.field = "field-1";
 *         em.persist(entity1);
 *
 *         List<org.acme.model.MyEntity> entities = em.createQuery("from org.acme.model.MyEntity", org.acme.model.MyEntity.class).getResultList();
 *     }
 * }
 */



@Entity
@NamedQuery(name = Student.GET_ALL_STUDENTS, query = "Select s from Student s")
public class Student {

    public static final String GET_ALL_STUDENTS = "GetAllStudents";
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)


    @Id
    private Long id;
    private String name;
    private String lastname;

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    @OneToOne
    private Profile profile;
    @ManyToMany
    private List<Course> courses;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public Profile getProfile() {
        return profile;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(lastname, student.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname);
    }
}