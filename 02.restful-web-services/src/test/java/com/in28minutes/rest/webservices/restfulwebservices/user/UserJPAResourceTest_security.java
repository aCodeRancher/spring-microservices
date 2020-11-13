package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserJPAResourceTest_security {

   @Autowired
   private MockMvc mockMvc;

   private Gson gson = new Gson();
   private Type userType = new TypeToken<User>(){}.getType();

   @Test
   @WithMockUser(username = "username", password="password")
    void listAllUsers() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/jpa/users")).andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> users = gson.fromJson(jsonResult, listType);
        assertTrue(users.size()==3);
        assertTrue(users.get(0).getName().equals("AB"));
        assertTrue(users.get(1).getName().equals("Jill"));
        assertTrue(users.get(2).getName().equals("Jam"));
    }

    @Test
    @WithMockUser(username ="username", password = "password")
    void createUserTest() throws Exception{

        String teresaUser = "{\"name\": \"Teresa\", \"birthDate\":\"2020-11-13T20:30:28.051+00:00\", \"posts\":[]}";
         mockMvc.perform(post("/jpa/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teresaUser)).andExpect(status().isCreated()).andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/jpa/users")).andReturn();
        String jsonResult = mvcResult.getResponse().getContentAsString();
        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> users = gson.fromJson(jsonResult, listType);
        assertTrue(users.size()==4);
        assertTrue(users.get(0).getName().equals("Teresa")) ;
    }
}