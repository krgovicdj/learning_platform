package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Course;
import org.acme.model.Student;
import org.acme.model.TimezoneInfo;
import org.acme.service.StudentService;
import org.acme.service.TimezoneService;

import java.util.List;

@Path("/student")
public class StudentResource {

    @Inject
    StudentService studentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addStudent")
    public String addStudent(Student student) {
        studentService.createStudent(student);
        return "Hello RESTEasy";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllStudents")
    @Transactional
    public Response getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return Response.ok().entity(students).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getStudentByName")
    @Transactional
    public Response getStudentByName(@QueryParam("name") String name){
        System.out.println("Name: "+name);
        List<Student> students = studentService.getStudentByName(name);
        return Response.ok().entity(students).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getStudentById/{id}")
    @Transactional
    public Response getStudentbyId(@PathParam("id") Long id){
        Student st=studentService.getStudentById(id);
        if(st==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(st).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{studentId}/courses")
    @Transactional
    public Response getCoursesForStudent(@PathParam("studentId") Long studentId){
        List<Course> courses = studentService.getCoursesForStudent(studentId);
        if(courses==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(courses).build();
    }

    @Inject
    TimezoneService timezoneService;

    @GET
    @Path("/getTimezoneByIP")
    @Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {
        try {
            TimezoneInfo timezoneInfo = timezoneService.getAndSaveTimezoneForUser(userId);
            return Response.ok(timezoneInfo).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
