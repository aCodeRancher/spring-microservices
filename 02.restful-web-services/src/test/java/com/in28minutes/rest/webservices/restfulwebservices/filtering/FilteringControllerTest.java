package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(FilteringController.class)
@AutoConfigureMockMvc
class FilteringControllerTest {

    @Autowired
    MockMvc mockMvc;

    //filter out value3
    @Test
    public void retrieveSomeBean() throws Exception{
         String expected = "{\"field1\":\"value1\",\"field2\":\"value2\"}";
          mockMvc.perform(get("/filtering"))
                  .andExpect(content().string(expected));
    }

    //filter out value 1
    @Test
    public void retrieveListOfSomeBeans() throws Exception{
        String expected = "[{\"field2\":\"value2\",\"field3\":\"value3\"},{\"field2\":\"value22\",\"field3\":\"value32\"}]";
        mockMvc.perform(get("/filtering-list"))
                .andExpect(content().string(expected));
    }
}