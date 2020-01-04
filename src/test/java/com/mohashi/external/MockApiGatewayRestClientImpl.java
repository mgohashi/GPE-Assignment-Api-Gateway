package com.mohashi.external;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Specializes;
import javax.ws.rs.NotFoundException;

import com.mohashi.model.Freelancer;
import com.mohashi.model.Project;
import com.mohashi.model.ProjectStatus;
import com.mohashi.model.Skill;

@Specializes
public class MockApiGatewayRestClientImpl extends ApiGatewayRestClientImpl {

    private List<Freelancer> freelancers = 
        Arrays.asList(
            new Freelancer(new Long(1), "John", "Cavil", "jcavil@gmail.com", Arrays.asList(new Skill(new Long(2), "Java"))), 
            new Freelancer(new Long(3), "Fred", "Tood", "ftood@gmail.com", Arrays.asList(new Skill(new Long(4), ".Net"))));

    private List<Project> projects = 
        Arrays.asList(
            new Project(1, "Bob", "Pris", "bpris@gmail.com", "New Mobile", "Mobile for all industries", ProjectStatus.OPEN), 
            new Project(2, "Ted", "Kuby", "tkuby@gmail.com", "New WebApp", "WebApp for all industries", ProjectStatus.IN_PROGRESS));

    @Override
    public Freelancer getFreelancer(Long freelancerId) throws NotFoundException {
        return freelancers.stream().filter(f -> f.getFreelancerId().equals(freelancerId))
            .findAny().orElseThrow(() -> new NotFoundException("Freelancer not found!"));
    }

    @Override
    public List<Freelancer> getFreelancers() {
        return freelancers;
    }

    @Override
    public Project getProject(Integer projectId) throws NotFoundException {
        return projects.stream().filter(p -> p.getProjectId() == projectId)
            .findAny().orElseThrow(() -> new NotFoundException("Project not found!"));
    }

    @Override
    public List<Project> getProjects() {
        return projects;
    }
    
}