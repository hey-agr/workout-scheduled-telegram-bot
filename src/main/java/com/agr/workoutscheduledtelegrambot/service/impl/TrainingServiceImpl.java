package com.agr.workoutscheduledtelegrambot.service.impl;

import com.agr.workoutscheduledtelegrambot.db.entity.Training;
import com.agr.workoutscheduledtelegrambot.db.repository.TrainingRepository;
import com.agr.workoutscheduledtelegrambot.service.TrainingService;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Training save(Training training) {
        return trainingRepository.save(training);
    }
}
