package com.codecool.supersprinter3000firstspring.controller.dto.developer;

import com.codecool.supersprinter3000firstspring.controller.dto.IdDisplayNamePair;

import java.util.List;
import java.util.UUID;

public record DeveloperDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        List<IdDisplayNamePair> userStories
) {
}
