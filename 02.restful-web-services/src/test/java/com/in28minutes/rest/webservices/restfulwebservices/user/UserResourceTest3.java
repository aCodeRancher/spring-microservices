package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(UserResource.class)
//Don't scan for another controller, UserJPAResource because the userRepository is not loaded by the WebMvcTest
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserJPAResource.class))
class UserResourceTest3 {

     @Autowired
     private MockMvc mockMvc;


    @MockBean
    private UserDaoService service;


    @Captor
    ArgumentCaptor<User> captor;


    @Test
    public void retrieveUser() throws Exception{
        when(service.findOne(10)).thenReturn(null);
         MockHttpServletResponse response = mockMvc.perform(get("/users/10")).andReturn().getResponse();
        assertTrue(response.getStatus()== HttpStatus.NOT_FOUND.value());
       verify(service,times(1) ).findOne(10);
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

}