package com.acm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

	@Test
	public void ValueTest() {
		assertEquals(5, 5, "Test");
	}
}
