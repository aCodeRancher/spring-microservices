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
@ActiveProfiles("nl")
public class HelloWorldControllerTest_nl {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int port;

    @Test
    public void helloWorldInternationalized() {

        String url = String.format("http://localhost:%d/%s", port, "/hello-world-internationalized");
        String expected = "Goede Morgen";
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url, HttpMethod.GET, null, String.class);
        responseEntity.getBody().contains(expected);
    }
}