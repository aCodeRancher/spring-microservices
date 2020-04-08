package com.in28minutes.microservices.limitsservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LimitsServiceApplicationTests {

	int port =8080 ;

	@Test
	//run  SpringCloudConfigServerApplication first, then run LimitsServiceApplication
	//next, run this test
	public void retrieveLimits(){
		String expected = "{\"maximum\":9999,\"minimum\":103}";
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:"+port+"/limits", String.class);
		assertTrue(response.getBody().contains(expected));
	}

	@Test
	//run the LimitServiceApplication and run this test, don't run the SpringCloudConfigServerApplication
	public void faultTolerateTest(){
		String expected = "{\"maximum\":999,\"minimum\":9}";
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:"+port+"/fault-tolerance-example", String.class);
		assertTrue(response.getBody().contains(expected));
	}

}
