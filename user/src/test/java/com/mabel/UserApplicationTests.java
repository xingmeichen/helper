package com.mabel;

import com.mabel.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserApplicationTests.class);

	@Autowired
	@Qualifier("teacher")
	private PersonService teacherService;

	@Autowired
	@Qualifier("student")
	private PersonService student;

	@Test
	public void contextLoads() {
	}

	@Test
	public void invokeTask() {
		String task1 = teacherService.task();
		LOGGER.info("task1 is {}", task1);
		String task2 = student.task();
		LOGGER.info("task2 is {}", task2);
	}
}
