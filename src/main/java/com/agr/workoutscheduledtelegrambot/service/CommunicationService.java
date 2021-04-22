package com.agr.workoutscheduledtelegrambot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class CommunicationService {
    private final ChatDataMessageHandler messageHandler;

    public CommunicationService(ChatDataMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public BotApiMethod<?> handleCallBackQuery(CallbackQuery callbackQuery) {
        return messageHandler.handleMessage(callbackQuery.getMessage().getChatId(), callbackQuery.getData());
    }

    public BotApiMethod<?> handleMessage(Message message) {
        return messageHandler.handleMessage(message.getChatId(), message.getText());
    }
}
