package com.agr.workoutscheduledtelegrambot.service.impl;

import com.agr.workoutscheduledtelegrambot.db.entity.User;
import com.agr.workoutscheduledtelegrambot.db.repository.UserRepository;
import com.agr.workoutscheduledtelegrambot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByChatIdWithTrainings(Long chatId) {
        return userRepository.getUserByChatIdWithTrainings(chatId)
                .orElse(save(User.builder()
                        .chatId(chatId)
                        .build()));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
