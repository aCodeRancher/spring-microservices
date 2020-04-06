package com.in28minutes.microservices.currencyconversionservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
class CurrencyConversionControllerTest {

   @Test
    public void convertCurrency() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR", String.class);
        assertTrue(response.getBody().contains("USD"));
        assertTrue(response.getBody().contains("8000") || response.getBody().contains("8001"));
    }
}