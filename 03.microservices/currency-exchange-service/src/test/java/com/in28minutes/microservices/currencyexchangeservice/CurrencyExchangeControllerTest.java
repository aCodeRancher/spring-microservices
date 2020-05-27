package com.in28minutes.microservices.currencyexchangeservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class CurrencyExchangeControllerTest {

    @Autowired
    MockMvc mock;

    @Test
    public void retrieveExchangeValue() throws Exception{
        MvcResult result = mock.perform(get("/currency-exchange/from/USD/to/INR")).andReturn();
        String output = result.getResponse().getContentAsString();
        assertTrue(output.contains("USD"));
        assertTrue(output.contains("INR"));
     }
}