package com.mohashi;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mohashi.external.ApiGatewayRestClient;
import com.mohashi.model.Freelancer;
import com.mohashi.model.Project;

import org.hamcrest.Matchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ApiGatewayResourceTest {
    @Inject
    private ApiGatewayRestClient mock;

    private static String port = "8080";
    private Client client;

    @Deployment
    public static WebArchive createDeployment() throws Exception {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "api-gateway.war")
        .addPackages(true, ApiGatewayApplication.class.getPackage())
        .addAsResource("project-local.yml", "project-defaults.yml")
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(war.toString(true));
        return war;
    }

    @Before
    public void before() throws Exception {
        client = ClientBuilder.newClient();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void whenThereAreFreelancers_thenReturnFreelancers() throws Exception {
        List<Freelancer> expected = mock.getFreelancers();

        WebTarget target2 = client.target("http://localhost:" + port).path("/gateway").path("/freelancers");
        Response response = target2.request(MediaType.APPLICATION_JSON).get();
        Assert.assertThat(response.getStatus(), Matchers.equalTo(new Integer(200)));

        ObjectMapper objectMapper = new ObjectMapper();
        List<Freelancer> returned = objectMapper.readValue(response.readEntity(String.class), new TypeReference<List<Freelancer>>(){});
        Assert.assertNotNull("The result shouldn't be null!", returned);
        Assert.assertEquals("The result shoult have 2 items", 2, returned.size());
        Assert.assertEquals("The result shoult be equal to the expected", expected, returned);
    }

    @Test
    public void whenThereAreFreelancers_thenReturnFreelancer() throws Exception {
        Freelancer expected = mock.getFreelancer(1l);

        WebTarget target2 = client.target("http://localhost:" + port).path("/gateway").path("/freelancers").path("/"+expected.getFreelancerId());
        Response response = target2.request(MediaType.APPLICATION_JSON).get();
        Assert.assertThat(response.getStatus(), Matchers.equalTo(new Integer(200)));

        ObjectMapper objectMapper = new ObjectMapper();
        Freelancer returned = objectMapper.readValue(response.readEntity(String.class), new TypeReference<Freelancer>(){});
        Assert.assertNotNull("The result shouldn't be null!", returned);
        Assert.assertEquals("The result shoult be equal to the expected", expected, returned);
    }

    @Test
    @RunAsClient
    public void whenThereArentFreelancers_thenReturnNotFound() throws Exception {
        WebTarget target2 = client.target("http://localhost:" + port).path("/gateway").path("/freelancers").path("/123");
        Response response = target2.request(MediaType.APPLICATION_JSON).get();
        System.out.println(response.readEntity(String.class));
        Assert.assertThat(response.getStatus(), Matchers.equalTo(new Integer(404)));
    }

    @Test
    public void whenThereAreProjects_thenReturnProjects() throws Exception {
        List<Project> expected = mock.getProjects();

        WebTarget target2 = client.target("http://localhost:" + port).path("/gateway").path("/projects");
        Response response = target2.request(MediaType.APPLICATION_JSON).get();
        Assert.assertThat(response.getStatus(), Matchers.equalTo(new Integer(200)));

        ObjectMapper objectMapper = new ObjectMapper();
        List<Project> returned = objectMapper.readValue(response.readEntity(String.class), new TypeReference<List<Project>>(){});
        Assert.assertNotNull("The result shouldn't be null!", returned);
        Assert.assertEquals("The result shoult have 2 items", 2, returned.size());
        Assert.assertEquals("The result shoult be equal to the expected", expected, returned);
    }

    @Test
    public void whenThereAreProjects_thenReturnProject() throws Exception {
        Project expected = mock.getProject(1);

        WebTarget target2 = client.target("http://localhost:" + port).path("/gateway").path("/projects").path("/"+expected.getProjectId());
        Response response = target2.request(MediaType.APPLICATION_JSON).get();
        Assert.assertThat(response.getStatus(), Matchers.equalTo(new Integer(200)));

        ObjectMapper objectMapper = new ObjectMapper();
        Project returned = objectMapper.readValue(response.readEntity(String.class), new TypeReference<Project>(){});
        Assert.assertNotNull("The result shouldn't be null!", returned);
        Assert.assertEquals("The result shoult be equal to the expected", expected, returned);
    }

    @Test
    @RunAsClient
    public void whenThereArentProjects_thenReturnNotFound() throws Exception {
        WebTarget target2 = client.target("http://localhost:" + port).path("/gateway").path("/projects").path("/123");
        Response response = target2.request(MediaType.APPLICATION_JSON).get();
        System.out.println(response.readEntity(String.class));
        Assert.assertThat(response.getStatus(), Matchers.equalTo(new Integer(404)));
    }

}