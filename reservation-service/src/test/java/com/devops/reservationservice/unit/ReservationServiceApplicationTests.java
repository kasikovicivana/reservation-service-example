package com.devops.reservationservice.unit;

import com.devops.reservationservice.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReservationServiceApplicationTests {

	@Autowired
	TestService testService;

	@Test
	void contextLoads() {
	}

	@Test
	void testService(){
		System.out.println("test krece");
		String res = testService.test();
		assertEquals("Welcome from reservation-service", res);
	}

}
