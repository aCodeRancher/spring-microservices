package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @Test
    void retrieveAllUsers() {
          List<User> allUsers = new ArrayList<User>();
          allUsers.add(new User(1, "Adam", new Date()));
          when(controller.retrieveAllUsers()).thenReturn(allUsers);
          List<User> returnUsers = controller.retrieveAllUsers();
          assertTrue(returnUsers.size()==1);
          verify(service,times(1)).findAll();
    }


}