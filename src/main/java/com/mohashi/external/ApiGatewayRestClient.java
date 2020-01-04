package com.mohashi.external;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.mohashi.model.Freelancer;
import com.mohashi.model.Project;

public interface ApiGatewayRestClient {
    List<Project> getProjects();

    Project getProject(Integer projectId) throws NotFoundException;

    List<Freelancer> getFreelancers();

    Freelancer getFreelancer(Long freelancerId) throws NotFoundException;
}