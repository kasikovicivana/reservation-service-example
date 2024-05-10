package com.devops.reservationservice.controller;

import com.devops.reservationservice.model.Admin;
import com.devops.reservationservice.repository.TestRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.List;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TestRepository testRepository;

    @LocalServerPort
    private Integer port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        testRepository.deleteAll();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
    @Test
    public void testGetTestEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/test")
                .then()
                .statusCode(200);
        List<Admin> admins = this.testRepository.findAll();
        System.out.println(admins);
        System.out.println("test");
    }
}
