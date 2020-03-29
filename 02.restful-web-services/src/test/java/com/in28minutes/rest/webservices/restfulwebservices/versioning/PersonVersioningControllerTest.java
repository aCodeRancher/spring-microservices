package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(PersonVersioningController.class)
@AutoConfigureMockMvc
class PersonVersioningControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void personV1() throws Exception{
        String expected = "{\"name\":\"Bob Charlie\"}";
        mockMvc.perform(get("/v1/person"))
                .andExpect(content().string(expected));
    }

    @Test
    public void personV2() throws Exception{
        String expected = "{\"name\":{\"firstName\":\"Bob\",\"lastName\":\"Charlie\"}}";
        mockMvc.perform(get("/v2/person"))
                .andExpect(content().string(expected));
    }

    @Test
    public void paramV1() throws Exception{
        String expected = "{\"name\":\"Bob Charlie\"}";
        mockMvc.perform(get("/person/param?version=1"))
                .andExpect(content().string(expected));
    }

    @Test
    public void paramV2() throws Exception{
        String expected = "{\"name\":{\"firstName\":\"Bob\",\"lastName\":\"Charlie\"}}";
        mockMvc.perform(get("/person/param?version=2"))
                .andExpect(content().string(expected));
    }

    @Test
    public void headerV1() throws Exception{
        String expected = "{\"name\":\"Bob Charlie\"}";
        mockMvc.perform(get("/person/header").header("X-API-VERSION","1"))
                .andExpect(content().string(expected));
    }

    @Test
    public void headerV2() throws Exception{
        String expected = "{\"name\":{\"firstName\":\"Bob\",\"lastName\":\"Charlie\"}}";
        mockMvc.perform(get("/person/header").header("X-API-VERSION","2"))
                .andExpect(content().string(expected));
    }

    @Test
    public void producesV1() throws Exception{
        String expected = "{\"name\":\"Bob Charlie\"}";
        mockMvc.perform(get("/person/produces").accept("application/vnd.company.app-v1+json"))
                .andExpect(content().string(expected));
    }

    @Test
    public void producesV2() throws Exception{
        String expected = "{\"name\":{\"firstName\":\"Bob\",\"lastName\":\"Charlie\"}}";
        mockMvc.perform(get("/person/produces").accept("application/vnd.company.app-v2+json"))
                .andExpect(content().string(expected));
    }


}