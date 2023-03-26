package com.codecool.supersprinter3000firstspring.service;


import com.codecool.supersprinter3000firstspring.controller.dto.userstory.NewUserStoryDto;
import com.codecool.supersprinter3000firstspring.controller.dto.userstory.UserStoryDto;
import com.codecool.supersprinter3000firstspring.entity.Developer;
import com.codecool.supersprinter3000firstspring.entity.UserStory;
import com.codecool.supersprinter3000firstspring.mapper.UserStoryMapper;
import com.codecool.supersprinter3000firstspring.repository.DeveloperRepository;
import com.codecool.supersprinter3000firstspring.repository.UserStoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final DeveloperRepository developerRepository;
    private final UserStoryMapper userStoryMapper;

    public UserStoryService(UserStoryRepository userStoryRepository, DeveloperRepository developerRepository, UserStoryMapper userStoryMapper) {
        this.userStoryRepository = userStoryRepository;
        this.developerRepository = developerRepository;
        this.userStoryMapper = userStoryMapper;
    }

    public List<UserStoryDto> getAllUserStories() {
        return userStoryRepository.findAllByOrderById().stream()
                .map(userStoryMapper::mapUserStoryEntityToDto)
                .toList();
    }

    public UserStoryDto addNewUserStory(NewUserStoryDto newUserStory) {
        UserStory entity = userStoryMapper.mapUserStoryDtoToEntity(newUserStory);
        UserStory savedUserStory =  userStoryRepository.save(entity);
        return userStoryMapper.mapUserStoryEntityToDto(savedUserStory);
    }



    public UserStoryDto getStory(UUID id) {
        return userStoryRepository.findById(id)
                .map(userStoryMapper::mapUserStoryEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void assignStoryToDeveloper(UUID userStoryId, UUID developerId) {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        userStory.assignDeveloper(developer);
        developer.addUserStory(userStory);

        //userStoryRepository.save(userStory);
        developerRepository.save(developer);




    }

    public List<UserStoryDto> getAllUserStoriesWithoutDeveloper() {
        return userStoryRepository.findAll().stream()
                .filter(UserStory::hasNoDeveloperAssigned)
                .map(userStoryMapper::mapUserStoryEntityToDto)
                .toList();
    }

}
