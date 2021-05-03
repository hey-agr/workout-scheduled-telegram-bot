package com.agr.workoutscheduledtelegrambot.db.repository;

import com.agr.workoutscheduledtelegrambot.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from user u left join fetch u.trainings where u.chatId = :chatId")
    Optional<User> getUserByChatIdWithTrainings(@Param("chatId") Long chatId);
}
