package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserResourceTest_security {

   @LocalServerPort
   int port;

   @Test
    public void retrieveAllUsers() {
        TestRestTemplate testRestTemplate = new TestRestTemplate("username", "password");
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:"+port+"/users", List.class);
        assertTrue(response.getStatusCodeValue()==200);
        List<User> result = response.getBody();
        assertTrue(result.size()==3);
    }

    @Test
    //Since the credentials are wrong, it will navigate to the sign in page.
    public void retrieveAllUser_notAuthenticated(){
        TestRestTemplate testRestTemplate = new TestRestTemplate("noUser", "noPassword");
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:"+port+"/users",String.class);
        assertTrue(response.getStatusCodeValue()==401);

    }

}