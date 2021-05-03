package com.agr.workoutscheduledtelegrambot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
class WorkoutScheduledTelegramBotApplicationTests {

	@Test
	void contextLoads() {
	}

}
