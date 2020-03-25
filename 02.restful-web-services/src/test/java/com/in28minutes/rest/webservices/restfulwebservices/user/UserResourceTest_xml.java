package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserResourceTest_xml {

    @Autowired
    MockMvc mvc;

    @Test
    public void retrieveUser() throws Exception {
        String returnUserID = "<id>1</id>";
        String returnUserName ="<name>Adam</name>";
        MvcResult result = mvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_XML)).andReturn();
        String contentAsReturn = result.getResponse().getContentAsString();
        contentAsReturn.contains(returnUserID);
        contentAsReturn.contains(returnUserName);

    }

    @Test
    public void createUser() throws Exception {
        String userInput = "<item><name>Alan</name><birthDate>2020-02-25T15:23:35.041+0000</birthDate></item>";
         mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                 .content(userInput)
         ).andExpect(status().is2xxSuccessful());

    }
}