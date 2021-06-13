package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext (classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserResourceTest_security {

   @LocalServerPort
   int port;

   @Test
    public void retrieveAllUsers() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:"+port+"/users", List.class);
        assertTrue(response.getStatusCodeValue()==200);
        List<User> result = response.getBody();
        assertTrue(result.size()==3);
    }

    @Test
    public void postUser() {
        TestRestTemplate testRestTemplate = new TestRestTemplate("username", "password");
        User aUser = new User();
        aUser.setId(876);
        aUser.setName("Tester");
        aUser.setBirthDate(Date.valueOf("1990-1-1"));
        ResponseEntity<User> response = testRestTemplate.postForEntity("http://localhost:"+port+"/users", aUser, User.class);
        assertTrue(response.getStatusCodeValue()==201);

    }
    @Test
    //Since the credentials are wrong, it will navigate to the sign in page.
    public void retrieveAllUser_notAuthenticated(){
        TestRestTemplate testRestTemplate = new TestRestTemplate("noUser", "noPassword");
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:"+port+"/users",String.class);
        assertTrue(response.getStatusCodeValue()==401);

    }

}