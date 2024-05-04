package com.devops.reservationservice.unit;

import com.devops.reservationservice.repository.TestRepository;
import com.devops.reservationservice.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class ReservationServiceApplicationTests {

    @Autowired
    TestService testService;

    @Mock
    TestRepository testRepository;

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

