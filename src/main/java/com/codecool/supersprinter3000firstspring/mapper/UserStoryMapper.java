package com.codecool.supersprinter3000firstspring.mapper;

import com.codecool.supersprinter3000firstspring.controller.dto.IdDisplayNamePair;
import com.codecool.supersprinter3000firstspring.controller.dto.userstory.NewUserStoryDto;
import com.codecool.supersprinter3000firstspring.controller.dto.userstory.UserStoryDto;
import com.codecool.supersprinter3000firstspring.entity.UserStory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserStoryMapper {
    public UserStoryDto mapUserStoryEntityToDto(UserStory entity) {
        return new UserStoryDto(
                entity.getId(),
                entity.getTitle(),
                entity.getStory(),
                entity.getAcceptanceCriteria(),
                entity.getEstimation(),
                entity.getBusinessValue(),
                entity.getUserStoryStatus(),
                getAssignedDevelopers(entity)
        );
    }

    private static List<IdDisplayNamePair> getAssignedDevelopers(UserStory entity) {

        return entity.getDevelopers().stream()
                .map(d -> new IdDisplayNamePair(d.getId(), d.getFullName()))
                .toList();

    }

    public UserStory mapUserStoryDtoToEntity(NewUserStoryDto newUserStory) {
        return new UserStory(
                newUserStory.title(),
                newUserStory.description(),
                newUserStory.acceptanceCriteria(),
                newUserStory.estimation(),
                newUserStory.businessValue(),
                newUserStory.status()
        );
    }
}
