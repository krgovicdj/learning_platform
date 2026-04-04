package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.model.Course;

import java.util.List;

@Dependent
public class CourseService {
    @Inject
    EntityManager em;

    @Transactional
    public void createCourse(Course course){
        em.merge(course);
    }

    @Transactional
    public List<Course> getAllCourses(){
        List<Course> courses= em.createNamedQuery(Course.GET_ALL_COURSES, Course.class).getResultList();
        for(Course course:courses){
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
}
