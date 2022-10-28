package com.github.serhx4;

import com.github.serhx4.web.HomeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BurgerHubApplicationTests {

	@Autowired
	HomeController homeController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(homeController);
	}

}
