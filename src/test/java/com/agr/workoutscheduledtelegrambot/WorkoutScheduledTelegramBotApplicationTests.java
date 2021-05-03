package com.agr.workoutscheduledtelegrambot;

import com.agr.workoutscheduledtelegrambot.db.entity.User;
import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import com.agr.workoutscheduledtelegrambot.model.ChatState;
import com.agr.workoutscheduledtelegrambot.service.ChatDataCacheService;
import com.agr.workoutscheduledtelegrambot.service.ChatDataMessageHandler;
import com.agr.workoutscheduledtelegrambot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
class WorkoutScheduledTelegramBotApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private ChatDataCacheService cacheService;

	@Test
	void testCreateOrFindUser() {
		Long chatId = 12345L;
		userService.getUserByChatIdWithTrainings(chatId);
		Assert.notNull(userService.getUserByChatIdWithTrainings(chatId), "user with chatID=" + chatId + " is null");
	}

	@Test
	void testCreateTraining() {
		final Long testChatId = 12345L;
		final String testTrainingName = "new training12345";
		ChatDataModel chatDataModel = new ChatDataModel(testChatId);
		chatDataModel.setChatState(ChatState.CREATE_TRAINING);
		cacheService.doActions(chatDataModel, testTrainingName);
		User user = userService.getUserByChatIdWithTrainings(testChatId);
		Assert.notNull(user, "user with chatID=" + testChatId + " is null");
		Assert.notEmpty(user.getTrainings(), "user with chatID=" + testChatId + " has empty trainings");
		Assert.isTrue(user.getTrainings().stream()
				.anyMatch(training -> training.getName().equals(testTrainingName))
				,"user with chatID=" + testChatId + " training name not equal " + testTrainingName);
	}

}
