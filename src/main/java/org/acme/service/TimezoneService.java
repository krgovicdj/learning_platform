package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.acme.model.Student;
import org.acme.model.TimezoneApiResponse;
import org.acme.model.TimezoneInfo;

@ApplicationScoped
public class TimezoneService {

    @Inject
    StudentService studentService;

    @Inject
    jakarta.persistence.EntityManager em;

    @Transactional
    public TimezoneInfo getAndSaveTimezoneForUser(Long userId) throws Exception {
        Student student = studentService.getStudentById(userId);
        if (student == null) {
            throw new NotFoundException("Korisnik sa ID " + userId + " nije pronađen");
        }

        try {
            String ipAddress = ClientBuilder.newClient()
                    .target("https://api.ipify.org")
                    .request()
                    .get(String.class);

            TimezoneApiResponse apiResponse = ClientBuilder.newClient()
                    .target("https://timeapi.io/api/time/current/ip")
                    .queryParam("ipAddress", ipAddress)
                    .request()
                    .get(TimezoneApiResponse.class);

            TimezoneInfo timezoneInfo = new TimezoneInfo();
            timezoneInfo.setTimeZone(apiResponse.getTimeZone());
            timezoneInfo.setDateTime(apiResponse.getDateTime());
            timezoneInfo.setIpAddress(ipAddress);
            timezoneInfo.setStudent(student);

            em.persist(timezoneInfo);

            return timezoneInfo;

        } catch (Exception e) {
            throw new WebApplicationException("Greška: " + e.getMessage(),
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}