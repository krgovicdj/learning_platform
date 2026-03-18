package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Profile;
import org.acme.model.Student;
import org.acme.service.ProfileService;
import org.acme.service.StudentService;

import java.util.List;

@Path("/profile")
public class ProfileResource {
    @Inject
    ProfileService profileService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addProfile")
    public String addProfile(Profile profile) {
        profileService.createProfile(profile);
        return "Hello RESTEasy";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllProfiles")
    public Response getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        return Response.ok().entity(profiles).build();
    }
}
