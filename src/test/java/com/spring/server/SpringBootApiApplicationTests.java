package com.spring.server;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.server.util.ValidationUtil;

//@SpringBootTest
class SpringBootApiApplicationTests {

	@Test
	void contextLoads() {
		assertFalse(ValidationUtil.notMatch("^/[a-zA-Z0-9/*-_]+", "/asd/1/2/**/_/-"));
	}

}
