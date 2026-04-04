package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.model.Course;
import org.acme.model.Student;

import java.util.List;

@Dependent
public class StudentService {

    @Inject
    EntityManager em;

    @Transactional
    public void createStudent(Student student) {
        em.merge(student);
    }

    @Transactional
    public List<Student> getAllStudents() {
        return em.createNamedQuery(Student.GET_ALL_STUDENTS, Student.class).getResultList();
    }

    @Transactional
    public Student getStudentById(Long id) {
        return em.find(Student.class, id);
    }


    @Transactional
    public List<Course> getCoursesForStudent(Long id) {
        Student st = em.find(Student.class, id);
        if (st == null) {
            return null;
        }
        List<Course> courses = st.getCourses();

        // Inicijalizuj sve LAZY kolekcije za svaki kurs
        for (Course course : courses) {
            if (course.getStudents() != null) {
                course.getStudents().size();
            }
            if (course.getLessons() != null) {
                course.getLessons().size();
            }
            if (course.getCategories() != null) {
                course.getCategories().size();
            }
            if (course.getInstructor() != null) {
                course.getInstructor().getName();
            }
        }
        return courses;
    }
    @Transactional
    public List<Student> getStudentByName(String name) {
        return em.createNamedQuery(Student.GET_STUDENT_BY_NAME, Student.class).setParameter("name", name).getResultList();
    }



}

