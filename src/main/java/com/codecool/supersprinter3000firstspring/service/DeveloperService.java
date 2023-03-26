package com.codecool.supersprinter3000firstspring.service;

import com.codecool.supersprinter3000firstspring.controller.dto.developer.DeveloperDto;
import com.codecool.supersprinter3000firstspring.controller.dto.developer.NewDeveloperDto;
import com.codecool.supersprinter3000firstspring.entity.Developer;
import com.codecool.supersprinter3000firstspring.mapper.DeveloperMapper;
import com.codecool.supersprinter3000firstspring.repository.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperMapper developerMapper;


    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAllBy().stream()
                .map(developerMapper::mapDeveloperEntityToDto)
                .toList();
    }
    public List<DeveloperDto> getAllDevelopers(Pageable pageable) {
        return developerRepository.findAllBy(pageable).stream()
                .map(developerMapper::mapDeveloperEntityToDto)
                .toList();
    }

    public DeveloperDto getDeveloper(UUID id) {
        return developerRepository.findOneById(id)
                .map(developerMapper::mapDeveloperEntityToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public DeveloperDto addNewDeveloper(NewDeveloperDto newDeveloper) {
        Developer developer = developerMapper.mapDeveloperDtoToEntity(newDeveloper);
        Developer dbDeveloper = developerRepository.save(developer);
        return developerMapper.mapDeveloperEntityToDto(dbDeveloper);
    }

    public List<DeveloperDto> getTopBusyDevelopers(Long n) {
        return developerRepository.findAllBy().stream()
                .sorted(Comparator.comparingInt(Developer::getUserStoriesCount).reversed())
                .limit(n)
                .map(developerMapper::mapDeveloperEntityToDto)
                .toList();
    }


    public void softDelete(UUID id) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        developer.setFirstName("*****");
        developer.setLastName("*****");
        developer.setEmail("*****");
        developer.clearDeveloperUserStories();
        developerRepository.save(developer);

    }

}
