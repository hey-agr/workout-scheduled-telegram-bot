package com.agr.workoutscheduledtelegrambot.service;

import com.agr.workoutscheduledtelegrambot.db.entity.Training;

public interface TrainingService {
    Training save(Training training);
}
