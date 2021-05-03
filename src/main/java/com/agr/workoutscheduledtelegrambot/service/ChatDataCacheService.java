package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.db.entity.Training;
import com.agr.workoutscheduledtelegrambot.db.entity.User;
import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import com.agr.workoutscheduledtelegrambot.model.ChatState;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatDataCacheService {
    private final ConcurrentHashMap<Long, ChatDataModel> cache = new ConcurrentHashMap<>();

    private final TrainingService trainingService;

    private final UserService userService;

    public ChatDataCacheService(TrainingService trainingService, UserService userService) {
        this.trainingService = trainingService;
        this.userService = userService;
    }

    private ChatDataModel initChatCache(@NotNull Long chatId) {
        ChatDataModel chatData = new ChatDataModel(chatId);
        chatData.setChatState(ChatState.MAIN_MENU);
        return cache.put(chatId, chatData);
    }

    public ChatDataModel getCache(@NotNull Long chatId) {
        return cache.getOrDefault(chatId, initChatCache(chatId));
    }

    public void doActions(ChatDataModel chatData, String data) {
        if (isBackCommand(data)) {
            chatData.setChatState(resolvePrevState(chatData));
        } else if (isCallbackData(data)) {
            chatData.setChatState(ChatState.valueOf(data));
        } else {
            if (chatData.getChatState() == ChatState.CREATE_TRAINING && !data.isEmpty()) {
                chatData.setChatState(ChatState.TRAINING_PROGRAMS);
                User user = userService.getUserByChatIdWithTrainings(chatData.getChatId());
                Training training = trainingService.save(Training.builder()
                        .name(data)
                        .build());
                user.addTraining(training);
                userService.save(user);
            }
        }
    }

    private ChatState resolvePrevState(ChatDataModel chatData) {
        switch (chatData.getChatState()) {
            case TRAINING_PROGRAMS: {
                return ChatState.MAIN_MENU;
            }
            case CREATE_TRAINING: {
                return ChatState.TRAINING_PROGRAMS;
            }
            default:
                return ChatState.MAIN_MENU;
        }
    }

    private boolean isCallbackData(String data) {
        return ChatState.contains(data);
    }

    private boolean isBackCommand(String data) {
        return isCallbackData(data) && ChatState.valueOf(data) == ChatState.BACK;
    }
}
