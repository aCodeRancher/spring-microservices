package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("en")
public class HelloWorldControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;

    @Test
    public void helloWorld() {
        String url = String.format("http://localhost:%d/%s", port, "/hello-world");
        String expected ="Hello World";
        //No request entity is needed
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, null, String.class);
        responseEntity.getBody().contains(expected);
    }

    @Test
    public void helloWorldBean() {
        String url = String.format("http://localhost:%d/%s", port, "/hello-world-bean");
        String expected ="Hello World";
        //No request entity is needed
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, null, String.class);
        responseEntity.getBody().contains(expected);

    }

    @Test
    public void helloWorldPathVariable() {
        String url = String.format("http://localhost:%d/%s", port, "/hello-world/path-variable/John");
        String expected = String.format("Hello World, %s", "John");
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, null, String.class);
        responseEntity.getBody().contains(expected);
    }


    @Test
    public void helloWorldInternationalized() {
        String url = String.format("http://localhost:%d/%s", port, "/hello-world-internationalized");
        String expected = "Good Morning";
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, null, String.class);
        responseEntity.getBody().contains(expected);
    }
}