package com.agr.workoutscheduledtelegrambot.model;

import lombok.Data;

@Data
public class ChatDataModel {
    private final Long chatId;

    private ChatState chatState;
}
