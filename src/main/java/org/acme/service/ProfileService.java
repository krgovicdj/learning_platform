package org.acme.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.model.Profile;
import java.util.List;

@Dependent
public class ProfileService {

    @Inject
    EntityManager em;

    @Transactional
    public void createProfile(Profile profile){
        em.merge(profile);
    }

    @Transactional
    public List<Profile> getAllProfiles(){
        return em.createNamedQuery(Profile.GET_ALL_PROFILES, Profile.class).getResultList();
    }


}
