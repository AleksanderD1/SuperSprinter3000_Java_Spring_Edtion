package com.codecool.supersprinter3000firstspring.mapper;

import com.codecool.supersprinter3000firstspring.controller.dto.IdDisplayNamePair;
import com.codecool.supersprinter3000firstspring.controller.dto.developer.DeveloperDto;
import com.codecool.supersprinter3000firstspring.controller.dto.developer.NewDeveloperDto;
import com.codecool.supersprinter3000firstspring.entity.Developer;
import com.codecool.supersprinter3000firstspring.entity.UserStory;
import org.springframework.stereotype.Component;

@Component
public class DeveloperMapper {


    public DeveloperDto mapDeveloperEntityToDto(Developer entity) {
        return new DeveloperDto(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getUserStories().stream()
                        .map(us -> getIdDisplayNamePair(us))
                        .toList()
        );
    }

    private static IdDisplayNamePair getIdDisplayNamePair(UserStory us) {
        return new IdDisplayNamePair(us.getId(), us.getTitle());
    }


    public Developer mapDeveloperDtoToEntity(NewDeveloperDto newDeveloper) {
        return new Developer(
                newDeveloper.firstName(),
                newDeveloper.lastName(),
                newDeveloper.email()

        );
    }

}
