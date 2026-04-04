package org.acme.service;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.logging.Logger;

@ApplicationScoped
public class SchedulerService {

    private static final Logger LOG = Logger.getLogger(SchedulerService.class.getName());

    @Inject
    EntityManager em;

    @Scheduled(every = "15s")
    void logStatistics() {
        Long brojStudenata = em.createQuery("SELECT COUNT(s) FROM Student s", Long.class).getSingleResult();
        Long brojKurseva = em.createQuery("SELECT COUNT(c) FROM Course c", Long.class).getSingleResult();
        Long brojInstruktora = em.createQuery("SELECT COUNT(i) FROM Instructor i", Long.class).getSingleResult();

        LOG.info(String.format("[SCHEDULER] Platform: %d studenats, %d courses, %d instructors",
                brojStudenata, brojKurseva, brojInstruktora));
    }
}