package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.model.ChatDataModel;
import com.agr.workoutscheduledtelegrambot.model.ChatState;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReplyMarkupService {
    public ReplyKeyboard getReplyKeyboard(ChatDataModel chatDataModel) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        switch (chatDataModel.getChatState()) {
            case MAIN_MENU: {
                rowList.addAll(getMainMenuItems());
                break;
            }

        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private Collection<? extends List<InlineKeyboardButton>> getMainMenuItems() {
        List<List<InlineKeyboardButton>> mainMenuItems = new ArrayList<>();

        ArrayList<InlineKeyboardButton> trainingProgramItems = new ArrayList<>();
        InlineKeyboardButton trainingProgramButton = new InlineKeyboardButton().setText("Программы тренировок");
        trainingProgramButton.setCallbackData(ChatState.TRAINING_PROGRAMS.name());
        mainMenuItems.add(trainingProgramItems);

        ArrayList<InlineKeyboardButton> currentTrainingProgramItems = new ArrayList<>();
        InlineKeyboardButton currentTrainingProgram = new InlineKeyboardButton().setText("Текущая тренировка");
        currentTrainingProgram.setCallbackData(ChatState.CURRENT_TRAINING_PROGRAM.name());
        mainMenuItems.add(currentTrainingProgramItems);

        return mainMenuItems;
    }
}
