package com.ecom.api_gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		properties = {
				"spring.config.import=optional:configserver:",
				"eureka.client.enabled=false",
				"spring.cloud.discovery.enabled=false"
		}
)
class ApiGatewayApplicationTests {
	@Test
	void contextLoads() {
	}
}
