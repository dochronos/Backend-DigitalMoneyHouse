package com.example.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GatewayApplicationTests {

	@Test
	void contextLoads() {
		// Simple assertion to confirm test ran
		assertTrue(true, "Spring context loaded successfully");
	}
}
