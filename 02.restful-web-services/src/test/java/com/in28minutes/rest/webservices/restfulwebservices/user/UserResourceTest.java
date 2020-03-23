package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//This test just loads UserResource controller.
//The service layer is a mock.
//This test only tests the controller's retrieveAll method
//Advantage: a simple test without loading the whole spring context such as the UserDaoService
//UserJPAResource, UserRepository and etc
@WebMvcTest(UserResource.class)
class UserResourceTest {

    @Autowired
    UserResource controller;

    @MockBean
    UserDaoService service;

    User user ;

    @BeforeEach
    public void setUp(){
          user = new User(1,"Adam", new Date());
    }

    @Test
    public  void retrieveAllUsers() {
          List<User> allUsers = new ArrayList<>();
          allUsers.add(user);
          when(controller.retrieveAllUsers()).thenReturn(allUsers);
          List<User> returnUsers = controller.retrieveAllUsers();
          assertTrue(returnUsers.size()==1);
          verify(service,times(1)).findAll();
    }


    @Test
    public void retrieveUser(){
        when(service.findOne(1)).thenReturn(user);
        Resource<User> resourceUser =  controller.retrieveUser(1);
        assertTrue(resourceUser!=null);
        assertTrue(resourceUser.getLink("all-users") != null);
        verify(service,times(1)).findOne(1);
    }

    @Test
    public void retrieveNonExistingUser(){
        user = null;
        when(service.findOne(2)).thenReturn(user);
        assertThrows(UserNotFoundException.class, ()-> controller.retrieveUser(2) );
        verify(service,times(1)).findOne(2);
     }

     @Test
     public void deleteUser(){
        when(service.deleteById(1)).thenReturn(user);
        controller.deleteUser(1);
        verify(service,times(1)).deleteById(1);
     }

     @Test
     public void deleteNonExistingUser(){
        user = null;
        when(service.deleteById(1)).thenReturn(user);
        assertThrows(UserNotFoundException.class, ()-> controller.deleteUser(1));
     }

     @Test
     public void createUser(){
        //since the user is a request body, we need to create this mock servlet request.
         //reference : https://howtodoinjava.com/spring-boot2/testing/rest-controller-unit-test-example/
         MockHttpServletRequest request = new MockHttpServletRequest();
         RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         when(service.save(user)).thenReturn(user);
         ResponseEntity<Object> response = controller.createUser(user);
         assertTrue(response.getHeaders().getLocation().getPath().equals("/1"));
    }
}