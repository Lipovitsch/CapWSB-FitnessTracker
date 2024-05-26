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
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }

    Training toEntity(TrainingDto trainingDto) {
        return new Training(
                trainingDto.user(),
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed());
    }

    Training toEntityBasic(TrainingDtoBasic trainingDtoBasic) {
        User user = userProvider.getUser(trainingDtoBasic.userId())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + trainingDtoBasic.userId() + " not found"));

        return new Training(
                user,
                trainingDtoBasic.startTime(),
                trainingDtoBasic.endTime(),
                trainingDtoBasic.activityType(),
                trainingDtoBasic.distance(),
                trainingDtoBasic.averageSpeed());
    }
}
