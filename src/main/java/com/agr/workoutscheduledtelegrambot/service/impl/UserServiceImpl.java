package com.agr.workoutscheduledtelegrambot.service.impl;

import com.agr.workoutscheduledtelegrambot.db.entity.User;
import com.agr.workoutscheduledtelegrambot.db.repository.UserRepository;
import com.agr.workoutscheduledtelegrambot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User resolveUserByChatIdWithTrainings(Long chatId) {
        Optional<User> user = userRepository.getUserByChatIdWithTrainings(chatId);
        if (user.isEmpty()) {
            return save(User.builder()
                    .chatId(chatId)
                    .build());
        }
        return user.get();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
