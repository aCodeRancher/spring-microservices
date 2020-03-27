package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class UserResourceTest2 {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception{
        String userInput= "{\"name\":\"Alan\", \"birthDate\":\"2020-02-25T15:23:35.041+0000\", \"posts\":null}";
        mockMvc.perform(post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userInput))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUser_notValidated() throws Exception{
        String userInput= "{\"name\":\"A\", \"birthDate\":\"2020-02-25T15:23:35.041+0000\", \"posts\":null}";
        mockMvc.perform(post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userInput))
                .andExpect(status().isBadRequest());
    }
}