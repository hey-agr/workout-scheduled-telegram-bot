package com.agr.workoutscheduledtelegrambot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@SpringBootTest
class WorkoutScheduledTelegramBotApplicationTests {

	@Autowired
	private TelegramBotsApi api;

	@Test
	void contextLoads() {

	}

}
