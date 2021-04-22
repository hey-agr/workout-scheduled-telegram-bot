package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import org.springframework.stereotype.Service;

@Service
public class ReplyTextService {
    public String getReplyMessage(ChatDataModel chatDataModel) {
        return "Текущее меню: " + chatDataModel.getChatState();
    }
}
