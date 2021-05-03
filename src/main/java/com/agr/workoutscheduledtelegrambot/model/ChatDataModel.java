package com.agr.workoutscheduledtelegrambot.model;

import lombok.Data;

@Data
public class ChatDataModel {
    private final Long chatId;

    private ChatState chatState;

    private TrainingModel trainingModel;

    public boolean isTrainingChosen() {
        return getTrainingModel() != null;
    }
}
