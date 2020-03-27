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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Captor
    ArgumentCaptor<User> captor;
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
    public void createUser_notValidated() throws Exception{
        String userInput= "{\"name\":\"A\", \"birthDate\":\"2020-02-25T15:23:35.041+0000\", \"posts\":null}";
        User user = new User(20, "A", new Date());
        lenient().when(service.save(any())).thenReturn(user);
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON).content(userInput))
                .andExpect(status().isBadRequest());

        verify(service,times(0)).save(user);
    }


    @Test
    public void createUser() throws Exception{
        String userInput= "{\"name\":\"Alan\", \"birthDate\":\"2020-02-25T15:23:35.041+0000\", \"posts\":null}";
        User user = new User(20, "Alan", new Date());
        when(service.save(any())).thenReturn(user);
        mockMvc.perform(
                   post("/users")
                   .contentType(MediaType.APPLICATION_JSON)
                   .accept(MediaType.APPLICATION_JSON).content(userInput))
                .andExpect(status().isCreated());

        verify(service,times(1)).save(captor.capture());
    }
}