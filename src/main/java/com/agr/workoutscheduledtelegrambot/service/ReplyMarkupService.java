package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import com.agr.workoutscheduledtelegrambot.model.ChatState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyMarkupService {

    public ReplyKeyboard getReplyKeyboard(ChatDataModel chatDataModel) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        switch (chatDataModel.getChatState()) {
            case MAIN_MENU: {
                inlineKeyboardMarkup.setKeyboard(getMainMenuItems(chatDataModel));
                break;
            }
            case TRAINING_PROGRAMS: {
                inlineKeyboardMarkup.setKeyboard(getTrainingProgramMenuItems(chatDataModel));
                break;
            }
            case CREATE_TRAINING: {
                inlineKeyboardMarkup.setKeyboard(getCreateTrainingProgramMenuItems(chatDataModel));
                break;
            }
        }
        return inlineKeyboardMarkup;
    }

    private List<List<InlineKeyboardButton>> getMainMenuItems(ChatDataModel chatDataModel) {
        List<List<InlineKeyboardButton>> mainMenuItems = new ArrayList<>();

        InlineKeyboardButton trainingProgramButton = new InlineKeyboardButton("Программы тренировок");
        trainingProgramButton.setCallbackData(ChatState.TRAINING_PROGRAMS.name());
        mainMenuItems.add(List.of(trainingProgramButton));

        InlineKeyboardButton currentTrainingProgramButton = new InlineKeyboardButton("Текущая тренировка");
        currentTrainingProgramButton.setCallbackData(ChatState.CURRENT_TRAINING_PROGRAM.name());
        mainMenuItems.add(List.of(currentTrainingProgramButton));

        return mainMenuItems;
    }

    private List<List<InlineKeyboardButton>> getTrainingProgramMenuItems(ChatDataModel chatDataModel) {
        List<List<InlineKeyboardButton>> trainingProgramMenu = new ArrayList<>();

        InlineKeyboardButton createTrainingButton = new InlineKeyboardButton("Создать тренировку");
        createTrainingButton.setCallbackData(ChatState.CREATE_TRAINING.name());
        trainingProgramMenu.add(List.of(createTrainingButton));

        trainingProgramMenu.add(List.of(getBackButton()));

        return trainingProgramMenu;
    }

    private List<List<InlineKeyboardButton>> getCreateTrainingProgramMenuItems(ChatDataModel chatDataModel) {
        List<List<InlineKeyboardButton>> createTrainingProgramMenuItems = new ArrayList<>();
        createTrainingProgramMenuItems.add(List.of(getBackButton()));

        return createTrainingProgramMenuItems;
    }

    private InlineKeyboardButton getBackButton() {
        InlineKeyboardButton backButton = new InlineKeyboardButton("Назад..");
        backButton.setCallbackData(ChatState.BACK.name());
        return backButton;
    }
}