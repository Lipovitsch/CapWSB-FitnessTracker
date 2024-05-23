package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default List<Training> findAllByUserId(Long userId) {
        return findAll().stream()
                        .filter(training -> training.getUser().getId().equals(userId))
                        .toList();
    }

    default List<Training> findAllByEndTimeAfter(Date date){
        return findAll().stream()
                        .filter(training -> training.getEndTime().after(date))
                        .toList();
    }
}
