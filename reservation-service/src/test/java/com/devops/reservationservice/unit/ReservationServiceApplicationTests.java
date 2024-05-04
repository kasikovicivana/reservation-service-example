package com.devops.reservationservice.unit;

import com.devops.reservationservice.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationServiceApplicationTests {

    @Autowired
    TestService testService;

    @Test
    void contextLoads() {
    }

    @Test
    void testService(){
        System.out.println("test krece");
        String res = testService.testService();
        assertEquals("Welcome from reservation-service", res);
    }

}

