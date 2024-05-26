package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingService,TrainingProvider {

    private final TrainingRepository trainingRepository;

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsAfterDate(Date date) {
        return trainingRepository.findAllByEndTimeAfter(date);
    }

    @Override
    public List<Training> getTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType);
    }

    @Override
    public Training createTraining(Training training) {
        log.info("Creating Training {}", training);
        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, Training training) {
        if (trainingRepository.findById(id).isEmpty()) {
            throw new TrainingNotFoundException(id);
        }
        log.info("Updating Training with ID {}", id);

        // update only the fields that are not null or 0.0
        Training existingTraining = trainingRepository.findById(id).get();
        User user = Optional.ofNullable(training.getUser()).orElse(existingTraining.getUser());
        Date startTime = Optional.ofNullable(training.getStartTime()).orElse(existingTraining.getStartTime());
        Date endTime = Optional.ofNullable(training.getEndTime()).orElse(existingTraining.getEndTime());
        ActivityType activityType = Optional.ofNullable(training.getActivityType()).orElse(existingTraining.getActivityType());

        training.setId(id);
        training.setUser(user);
        training.setStartTime(startTime);
        training.setEndTime(endTime);
        training.setActivityType(activityType);

        if (training.getDistance() == 0.0) {
            training.setDistance(existingTraining.getDistance());
        }
        if (training.getAverageSpeed() == 0.0) {
            training.setAverageSpeed(existingTraining.getAverageSpeed());
        }

        return trainingRepository.save(training);
    }
}
