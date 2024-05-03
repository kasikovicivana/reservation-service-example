package com.devops.reservationservice.controller;

import com.devops.reservationservice.model.Admin;
import com.devops.reservationservice.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TestRepository testRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0").withUsername("postgres").withPassword("postgres").withNetworkAliases("devops");

    @Test
    public void testGetTestEndpoint() {
        String baseUrl = "/test";
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        System.out.println("greskaa---");
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), "Welcome from reservation-service");
        List<Admin> admins = this.testRepository.findAll();
        System.out.println(admins);
    }
}
