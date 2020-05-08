package com.in28minutes.microservices.currencyconversionservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest

class CurrencyConversionControllerTest {

   @Test
    public void convertCurrency_zuul() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR", String.class);
       String expected = "{\"id\":10001,\"from\":\"USD\",\"to\":\"INR\",\"conversionMultiple\":65.00,\"port\":8000}";
       String expected1= "{\"id\":10001,\"from\":\"USD\",\"to\":\"INR\",\"conversionMultiple\":65.00,\"port\":8001}";
       assertTrue(response.getBody().contains(expected) || response.getBody().contains(expected1));
    }

    @Test
    public void convertCurrency_feign() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8100/currency-converter-feign/from/USD/to/INR/quantity/100", String.class);
        String expected = "{\"id\":10001,\"from\":\"USD\",\"to\":\"INR\",\"conversionMultiple\":65.00,\"quantity\":100,\"totalCalculatedAmount\":6500.00,\"port\":8000}";
        String expected1= "{\"id\":10001,\"from\":\"USD\",\"to\":\"INR\",\"conversionMultiple\":65.00,\"quantity\":100,\"totalCalculatedAmount\":6500.00,\"port\":8001}";
        assertTrue(response.getBody().contains(expected) || response.getBody().contains(expected1));
    }

    @Test
    public void convertCurrency_exchangeService() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10", String.class);
        String expected = "{\"id\":10001,\"from\":\"USD\",\"to\":\"INR\",\"conversionMultiple\":65.00,\"quantity\":10,\"totalCalculatedAmount\":650.00,\"port\":8000}";
        assertTrue(response.getBody().contains(expected));
    }
}