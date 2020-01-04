package com.mohashi.model;

import java.util.ArrayList;
import java.util.List;

public class Freelancer {
    private Long freelancerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private List<Skill> skills = new ArrayList<>();

    public Freelancer() {}

    public Freelancer(Long freelancerId, String firstName, String lastName, String emailAddress,
            List<Skill> skills) {
        this.freelancerId = freelancerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.skills = skills;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((freelancerId == null) ? 0 : freelancerId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Freelancer other = (Freelancer) obj;
        if (freelancerId == null) {
            if (other.freelancerId != null)
                return false;
        } else if (!freelancerId.equals(other.freelancerId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Freelancer [emailAddress=" + emailAddress + ", firstName=" + firstName + ", freelancerId="
                + freelancerId + ", lastName=" + lastName + ", skills=" + skills + "]";
    }
}