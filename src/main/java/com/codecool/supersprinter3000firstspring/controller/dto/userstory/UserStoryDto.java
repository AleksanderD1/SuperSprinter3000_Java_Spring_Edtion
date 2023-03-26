package com.codecool.supersprinter3000firstspring.controller.dto.userstory;

import com.codecool.supersprinter3000firstspring.controller.dto.IdDisplayNamePair;
import com.codecool.supersprinter3000firstspring.entity.UserStoryStatus;

import java.util.List;
import java.util.UUID;

public record UserStoryDto(
        UUID id,
        String title,
        String description,
        String acceptanceCriteria,
        Double estimation,
        Integer businessValue,
        UserStoryStatus status,
        List<IdDisplayNamePair> assignedDeveloper
) {
}
