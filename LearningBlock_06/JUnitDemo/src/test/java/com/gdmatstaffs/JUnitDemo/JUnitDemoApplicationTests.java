package com.gdmatstaffs.JUnitDemo;

import com.gdmatstaffs.JUnitDemo.book.BookRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JUnitDemoApplicationTests
{
	@Autowired
	private BookRestController bookRestController;

	@Test
	void contextLoads() {
		assertNotNull(bookRestController);
	}
}
