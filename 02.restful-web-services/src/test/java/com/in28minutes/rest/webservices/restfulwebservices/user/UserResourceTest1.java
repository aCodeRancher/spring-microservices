package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exception.CustomizedResponseEntityExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//Test case with controller advice
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserResourceTest1 {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserResource controller;

    @Mock
    private UserDaoService service;


    @BeforeEach
    public void setUp(){
         this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                 .setControllerAdvice(new CustomizedResponseEntityExceptionHandler()).build();

    }

    @Test
    public void retrieveUser() throws Exception{
        when(service.findOne(10)).thenReturn(null);
        MockHttpServletResponse response = mockMvc.perform(get("/users/10")).andReturn().getResponse();
        assertTrue(response.getStatus()== HttpStatus.NOT_FOUND.value());
        verify(service,times(1) ).findOne(10);
    }

    @Test
    //use lenient to remove unnecessary stubbing.
    //service.save() method is never called as the user cannot be validated
    //reference https://www.baeldung.com/mockito-unnecessary-stubbing-exception
    public void createUser() throws Exception{
        User user = new User(null, "A", new Date());
        lenient().when(service.save(user)).thenReturn(user);
        MockHttpServletResponse response = mockMvc.perform(post("/users")).andReturn().getResponse();
        assertTrue(response.getStatus()== HttpStatus.BAD_REQUEST.value());
        verify(service,times(0)).save(user);
    }
}