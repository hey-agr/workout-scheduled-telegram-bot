package com.agr.workoutscheduledtelegrambot.db.repository;

import com.agr.workoutscheduledtelegrambot.db.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
}
