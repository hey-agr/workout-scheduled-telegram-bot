package com.agr.workoutscheduledtelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorkoutScheduledTelegramBotApplication {
	public static void main(String[] args) {
		SpringApplication.run(WorkoutScheduledTelegramBotApplication.class, args);
	}
}
