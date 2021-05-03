package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.db.entity.User;

public interface UserService {
    User resolveUserByChatIdWithTrainings(Long chatId);

    User save(User user);
}
