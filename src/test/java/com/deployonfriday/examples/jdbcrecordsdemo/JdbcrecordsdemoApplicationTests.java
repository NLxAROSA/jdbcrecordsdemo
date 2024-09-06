package com.deployonfriday.examples.jdbcrecordsdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbcrecordsdemoApplicationTests {

	@Autowired
	ScheduledSessionRepo scheduledSessionRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void testInsert()	{
		ScheduledSession scheduledSession = new ScheduledSession("testSession", "testPassCode", LocalDateTime.now());
		assertEquals(1, scheduledSessionRepo.insertScheduledSession(scheduledSession));
	}

	@Test
	void testSelect(){
		Optional<ScheduledSession> optionalSession = scheduledSessionRepo.getScheduledSession("mytestsession");
		assertTrue(optionalSession.isPresent());
		ScheduledSession session = optionalSession.get();
		assertEquals("mytestpasscode", session.passCode());
	}

}
