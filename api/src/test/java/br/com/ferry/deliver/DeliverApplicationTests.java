package br.com.ferry.deliver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.ferry.deliver.DeliverApplication.main;

@SpringBootTest
class DeliverApplicationTests {

	@Test
	void contextLoads() {
		main(new String[]{});
	}
}
