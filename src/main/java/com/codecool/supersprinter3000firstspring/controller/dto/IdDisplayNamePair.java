package com.codecool.supersprinter3000firstspring.controller.dto;

import java.util.UUID;

public record IdDisplayNamePair(
        UUID id,
        String displayName
) {
}
