package org.acme.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@NamedQuery(name = Profile.GET_ALL_PROFILES, query = "Select p from Profile p")
public class Profile {


    public static final String GET_ALL_PROFILES = "GetAllProfiles";
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_seq")
    @SequenceGenerator(name = "profile_seq", sequenceName = "profile_seq", allocationSize = 1)

    @Id
    private Long id;
    private String bio;
    private String experience;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Profile profile)) return false;
        return Objects.equals(getId(), profile.getId()) && Objects.equals(getBio(), profile.getBio()) && Objects.equals(getExperience(), profile.getExperience());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBio(), getExperience());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public String getExperience() {
        return experience;
    }
}
