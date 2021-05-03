package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.db.entity.User;
import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import org.springframework.stereotype.Service;

@Service
public class ReplyTextService {
    private final ChatDataCacheService chatDataCacheService;

    private final UserService userService;

    public ReplyTextService(ChatDataCacheService chatDataCacheService, UserService userService) {
        this.chatDataCacheService = chatDataCacheService;
        this.userService = userService;
    }

    public String getReplyMessage(ChatDataModel chatDataModel) {
        switch (chatDataModel.getChatState()) {
            case MAIN_MENU:
                return mainMenuMessage(chatDataModel);
            case CREATE_TRAINING:
                return createTrainingMessage(chatDataModel);
            case LAST_TRAINING_PROGRAM:
                return lastTrainingMessage(chatDataModel);
            case CURRENT_TRAINING_PROGRAM:
                return currentTrainingProgramMessage(chatDataModel);
            case TRAINING_PROGRAMS:
                return trainingProgramsMessage(chatDataModel);
        }
        return "Текущее меню: " + chatDataModel.getChatState();
    }

    private String mainMenuMessage(ChatDataModel chatDataModel) {
        return "Добро пожаловать в главное меню!\nВыберите дальнейшее действие:";
    }

    private String createTrainingMessage(ChatDataModel chatDataModel) {
        return "Введите наименование тренировки:";
    }

    private String lastTrainingMessage(ChatDataModel chatDataModel) {
        return "Тут будет последняя тренировка";
    }

    private String currentTrainingProgramMessage(ChatDataModel chatDataModel) {
        return chatDataModel.isTrainingChosen() ? "Текущая тренировка: " + chatDataModel.getTrainingModel().getName() : "Не выбрана тренировка";
    }

    private String trainingProgramsMessage(ChatDataModel chatDataModel) {
        User user = userService.getUserByChatIdWithTrainings(chatDataModel.getChatId());
        StringBuilder sb = new StringBuilder("Список тренировок:\n");
        user.getTrainings().forEach(training -> {
            sb.append(training.getName()).append("\n");
        });
        return sb.toString();
    }
}
