package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.model.Student;

import java.util.List;

@Dependent
    public class StudentService {

        @Inject
        EntityManager em;

        @Transactional
        public Student createStudent(Student student){
            return em.merge(student);
        }

        @Transactional
        public List<Student> getAllStudents(){
            return em.createNamedQuery(Student.GET_ALL_STUDENTS, Student.class).getResultList();
        }


    }

