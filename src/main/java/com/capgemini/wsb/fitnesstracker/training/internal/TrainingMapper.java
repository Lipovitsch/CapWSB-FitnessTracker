package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {
    private final UserProvider userProvider;

    @Autowired
    public TrainingMapper(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    Training toEntity(TrainingDto trainingDto) {
        User user = userProvider.getUser(trainingDto.user_id())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + trainingDto.user_id() + " not found"));

        return new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed());
    }
}
