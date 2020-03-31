package com.in28minutes.microservices.limitsservice;

import org.junit.jupiter.api.Test;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertTrue;



@SpringBootTest
public class LimitsServiceApplicationTests {

	int port = 8080;

	@Test
	public void retrieveLimits(){
		String expected = "{\"maximum\":2222,\"minimum\":2}";
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:"+port+"/limits", String.class);
		assertTrue(response.getBody().contains(expected));
	}

}
