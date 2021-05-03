package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ChatDataMessageHandler {
    private final ChatDataCacheService chatDataCacheService;
    private final ReplyMarkupService replyMarkupService;
    private final ReplyTextService replyTextService;

    public ChatDataMessageHandler(ChatDataCacheService chatDataCacheService,
                                  ReplyMarkupService replyMarkupService,
                                  ReplyTextService replyTextService) {
        this.chatDataCacheService = chatDataCacheService;
        this.replyMarkupService = replyMarkupService;
        this.replyTextService = replyTextService;
    }

    public SendMessage handleMessage(Long chatId, String data) {
        ChatDataModel chatData = chatDataCacheService.getCache(chatId);
        chatDataCacheService.doActions(chatData, data);
        return SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(replyTextService.getReplyMessage(chatData))
                .replyMarkup(replyMarkupService.getReplyKeyboard(chatData))
                .build();
    }

}
