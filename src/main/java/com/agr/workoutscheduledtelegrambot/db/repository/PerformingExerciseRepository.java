package com.agr.workoutscheduledtelegrambot.db.repository;

import com.agr.workoutscheduledtelegrambot.db.entity.PerformingExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerformingExerciseRepository extends JpaRepository<PerformingExercise, UUID> {
}
