package com.in28minutes.microservices.netflixeurekanamingserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import java.net.InetAddress;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NetflixEurekaNamingServerApplicationTest {

    @Test
    public void testEureka() throws Exception{
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        String ip_localhost = InetAddress.getLocalHost().getHostAddress();
        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8761", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(response.getBody().contains(ip_localhost));
    }
 }