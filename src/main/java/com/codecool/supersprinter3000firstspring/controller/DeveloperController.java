package com.codecool.supersprinter3000firstspring.controller;


import com.codecool.supersprinter3000firstspring.controller.dto.developer.DeveloperDto;
import com.codecool.supersprinter3000firstspring.controller.dto.developer.NewDeveloperDto;
import com.codecool.supersprinter3000firstspring.service.DeveloperService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }


    @GetMapping(params = {"page","size", "sort"})
    public List<DeveloperDto> getAllDevelopersWithPageable(Pageable pageable) {
        return developerService.getAllDevelopers(pageable);
    }

    @GetMapping("/{id}")
    public DeveloperDto getDeveloper(@PathVariable UUID id) { return developerService.getDeveloper(id); }

    @GetMapping("/mostBusyDeveloper/{limit}")
    public List<DeveloperDto> getTopBusyDeveloper(@PathVariable Long limit){
        return developerService.getTopBusyDevelopers(limit);
    }

    @PostMapping
    public DeveloperDto addNewDeveloper(@RequestBody NewDeveloperDto newDeveloper) {
        return developerService.addNewDeveloper(newDeveloper);
    }

    @DeleteMapping("/{id}")
    private void softDelete(@PathVariable UUID id){
        developerService.softDelete(id);

    }


}
