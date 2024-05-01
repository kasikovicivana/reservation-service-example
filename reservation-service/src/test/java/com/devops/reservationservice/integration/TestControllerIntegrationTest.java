package com.devops.reservationservice.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class TestControllerIntegrationTest {

    private final TestRestTemplate restTemplate;

    @Test
    public void testGetTestEndpoint() {
        String baseUrl = "http://localhost:9000/test";
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
