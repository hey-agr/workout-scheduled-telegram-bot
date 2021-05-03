package com.agr.workoutscheduledtelegrambot.model;

public enum ChatState {
    MAIN_MENU,
    TRAINING_PROGRAMS,
    CREATE_TRAINING,
    CURRENT_TRAINING_PROGRAM,
    LAST_TRAINING_PROGRAM,
    BACK;

    public static boolean contains(String value) {
        for (ChatState c : values()) {
            if (c.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
