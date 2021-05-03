package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.db.entity.User;

public interface UserService {
    User getUserByChatIdWithTrainings(Long chatId);

    User save(User user);
}
