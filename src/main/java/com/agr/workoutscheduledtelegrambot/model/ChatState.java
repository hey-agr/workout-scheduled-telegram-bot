package com.agr.workoutscheduledtelegrambot.model;

public enum ChatState {
    MAIN_MENU,
    TRAINING_PROGRAMS,
    CURRENT_TRAINING_PROGRAM;

    public static boolean contains(String value) {
        for (ChatState c : values()) {
            if (c.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
