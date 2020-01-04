package com.mohashi.external;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.mohashi.model.Freelancer;
import com.mohashi.model.Project;

@ApplicationScoped
public class ApiGatewayRestClientImpl implements ApiGatewayRestClient {
    public List<Project> getProjects() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(
            String.format("http://%s:%s", System.getenv("PROJECT_SERVICE_SERVICE_HOST"), System.getenv("PROJECT_SERVICE_SERVICE_PORT")));
        return target.path("/projects").request(MediaType.APPLICATION_JSON)
            .get(new GenericType<List<Project>>(){});
    }

    public Project getProject(Integer projectId) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(
            String.format("http://%s:%s", System.getenv("PROJECT_SERVICE_SERVICE_HOST"), System.getenv("PROJECT_SERVICE_SERVICE_PORT")));
        return target.path("/projects").path(projectId.toString()).request(MediaType.APPLICATION_JSON)
            .get(new GenericType<Project>(){});
    }

    public List<Freelancer> getFreelancers() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(
            String.format("http://%s:%s", System.getenv("FREELANCER_SERVICE_SERVICE_HOST"), System.getenv("FREELANCER_SERVICE_SERVICE_PORT")));
        return target.path("/freelancers").request(MediaType.APPLICATION_JSON)
            .get(new GenericType<List<Freelancer>>(){});
    }

    public Freelancer getFreelancer(Long freelancerId) throws NotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(
            String.format("http://%s:%s", System.getenv("FREELANCER_SERVICE_SERVICE_HOST"), System.getenv("FREELANCER_SERVICE_SERVICE_PORT")));
        return target.path("/freelancers").path(String.valueOf(freelancerId)).request(MediaType.APPLICATION_JSON)
            .get(new GenericType<Freelancer>(){});
    }
}