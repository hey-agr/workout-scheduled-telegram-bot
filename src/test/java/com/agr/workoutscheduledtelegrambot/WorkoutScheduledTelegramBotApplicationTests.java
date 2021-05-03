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

	@Autowired
	private ChatDataMessageHandler chatDataMessageHandler;

	@Test
	void testCreateOrFindUser() {
		Long testChatId = 12345L;
		userService.resolveUserByChatIdWithTrainings(testChatId);
		Assert.notNull(userService.resolveUserByChatIdWithTrainings(testChatId), "user with chatID=" + testChatId + " is null");
	}

	@Test
	void testCreateTraining() {
		final Long testChatId = 12345L;
		final String testTrainingName = "new training12345";
		final String testTrainingName1 = "new training1234543";
		ChatDataModel chatDataModel = new ChatDataModel(testChatId);
		chatDataModel.setChatState(ChatState.CREATE_TRAINING);
		cacheService.doActions(chatDataModel, testTrainingName);
		chatDataModel.setChatState(ChatState.CREATE_TRAINING);
		cacheService.doActions(chatDataModel, testTrainingName1);
		User user = userService.resolveUserByChatIdWithTrainings(testChatId);
		Assert.notNull(user, "user with chatID=" + testChatId + " is null");
		Assert.notEmpty(user.getTrainings(), "user with chatID=" + testChatId + " has empty trainings");
		Assert.isTrue(user.getTrainings().size() == 2, "user with chatID=" + testChatId + " trainings size != 2");
		Assert.isTrue(user.getTrainings().stream()
				.anyMatch(training -> training.getName().equals(testTrainingName))
				,"user with chatID=" + testChatId + " training name not equal " + testTrainingName);
	}

	@Test
	void testListTraining() {
		final Long testChatId = 12345L;
		final String testTrainingName = "new training12345";
		final String testTrainingName1 = "new training54321";

		User user = userService.resolveUserByChatIdWithTrainings(testChatId);
		Assert.notNull(user, "user with chatID=" + testChatId + " is null");

		ChatDataModel chatDataModel = new ChatDataModel(testChatId);
		chatDataModel.setChatState(ChatState.CREATE_TRAINING);
		cacheService.doActions(chatDataModel, testTrainingName);
		chatDataModel.setChatState(ChatState.CREATE_TRAINING);
		cacheService.doActions(chatDataModel, testTrainingName1);
		Assert.notEmpty(user.getTrainings(), "user with chatID=" + testChatId + " has empty trainings");
		Assert.isTrue(user.getTrainings().size() == 2, "user with chatID=" + testChatId + " trainings size != 2");
		Assert.isTrue(user.getTrainings().stream()
						.anyMatch(training -> training.getName().equals(testTrainingName))
				,"user with chatID=" + testChatId + " training name not equal " + testTrainingName);

		chatDataModel.setChatState(ChatState.TRAINING_PROGRAMS);
		chatDataMessageHandler.handleMessage(testChatId, ChatState.TRAINING_PROGRAMS.name());
	}

}
