package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import com.agr.workoutscheduledtelegrambot.model.ChatState;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatDataCacheService {
    private final ConcurrentHashMap<Long, ChatDataModel> cache = new ConcurrentHashMap<>();

    private ChatDataModel initChatCache(@NotNull Long chatId) {
        ChatDataModel chatData = new ChatDataModel(chatId);
        chatData.setChatState(ChatState.MAIN_MENU);
        return cache.put(chatId, chatData);
    }

    public ChatDataModel getCache(@NotNull Long chatId) {
        return cache.getOrDefault(chatId, initChatCache(chatId));
    }
}
