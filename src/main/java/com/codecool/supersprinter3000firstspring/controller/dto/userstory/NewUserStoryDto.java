package com.codecool.supersprinter3000firstspring.controller.dto.userstory;

import com.codecool.supersprinter3000firstspring.entity.UserStoryStatus;

public record NewUserStoryDto(
        String title,
        String description,
        String acceptanceCriteria,
        Double estimation,
        Integer businessValue,
        UserStoryStatus status
) {
}
