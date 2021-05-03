package com.agr.workoutscheduledtelegrambot.db.repository;

import com.agr.workoutscheduledtelegrambot.db.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}
