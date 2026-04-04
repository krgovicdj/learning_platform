package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Course;
import org.acme.service.CourseService;

import java.util.List;

@Path("/course")
public class CourseResource {
    @Inject
    CourseService courseService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addCourse")
    public String addCourse(Course course){
        courseService.createCourse(course);
        return "Hello RESTEasy";
    }

    @GET
    @Produces("application/json")
    @Path("/getAllCourses")
    @RolesAllowed("admin")
    @Transactional
    public Response getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return Response.ok().entity(courses).build();
    }


}
