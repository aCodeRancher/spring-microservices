package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;



@SpringBootTest
@AutoConfigureMockMvc
class UserResourceTest_security_1 {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void retrieveAllUser() throws Exception{
       MvcResult result = mockMvc.perform(get("/users")
                .with(user("username").password("password"))).andReturn();
       String response = result.getResponse().getContentAsString();
       assertTrue(response.contains("Adam"));
       assertTrue(response.contains("Eve"));
       assertTrue(response.contains("Jack"));

    }
   //add spring-boot-starter-security dependency to the pom before run this test
    @Test
    public void retrieveAllUser_notAuthenticated() throws Exception{
      mockMvc.perform(get("/users").with(httpBasic("u","p"))
                ).andExpect(status().isUnauthorized());
   }
}