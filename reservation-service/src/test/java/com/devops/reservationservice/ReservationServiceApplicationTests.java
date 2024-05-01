package com.devops.reservationservice;

import com.devops.reservationservice.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReservationServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testService(){
		System.out.println("test krece");
		TestService service = new TestService();
		String res = service.test();
		assertEquals("Welcome from reservation-service", res);
	}

}
