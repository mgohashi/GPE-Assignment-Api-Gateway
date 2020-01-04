package com.mohashi;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.mohashi.external.ApiGatewayRestClient;
import com.mohashi.model.Freelancer;
import com.mohashi.model.Project;

@Path("/gateway")
public class ApiGatewayResource {

    @Inject
    private ApiGatewayRestClient client;

    @GET
    @Path("/projects")
    @Produces("application/json")
    public List<Project> getProjects() {
        return client.getProjects();
    }

    @GET
    @Path("/projects/{id}")
    @Produces("application/json")
    public Project getProject(@PathParam("id") Integer projectId) {
        return client.getProject(projectId);
    }

    @GET
    @Path("/freelancers")
    @Produces("application/json")
    public List<Freelancer> getFreelancers() {
        return client.getFreelancers();
    }

    @GET
    @Path("/freelancers/{id}")
    @Produces("application/json")
    public Freelancer getFreelancer(@PathParam("id") Long freelancerId) {
        return client.getFreelancer(freelancerId);
    }
}
