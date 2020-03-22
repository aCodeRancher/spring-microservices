package com.in28minutes.springboot.basics.springbootin10steps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
@AutoConfigureMockMvc
class BooksControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAllBooksTest() throws Exception{
        String expected = "[{\"id\":1,\"name\":\"Mastering Spring 5.2\",\"author\":\"Ranga Karanam\"}]";
        mockMvc.perform(get("/books"))
                .andExpect(content().string(expected));
    }
}