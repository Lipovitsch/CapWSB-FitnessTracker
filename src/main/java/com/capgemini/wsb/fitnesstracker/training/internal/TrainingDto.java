package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.util.Date;

record TrainingDto(@Nullable Long id,
                   User user, Date startTime, Date endTime,
                   ActivityType activityType, double distance, double averageSpeed) {
}

record TrainingDtoBasic(@Nullable Long id,
                   Long userId, Date startTime, Date endTime,
                   ActivityType activityType, double distance, double averageSpeed) {
}
