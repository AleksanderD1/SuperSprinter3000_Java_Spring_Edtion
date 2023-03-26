package com.codecool.supersprinter3000firstspring.controller;

import com.codecool.supersprinter3000firstspring.controller.dto.userstory.NewUserStoryDto;
import com.codecool.supersprinter3000firstspring.controller.dto.userstory.UserStoryDto;
import com.codecool.supersprinter3000firstspring.service.UserStoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-stories")
//@AllArgsConstructor
public class UserStoryController {

    private final UserStoryService userStoryService;


    //    @Autowired
    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }


    @GetMapping
    public List<UserStoryDto> getAllUserStories() {
        return userStoryService.getAllUserStories();
    }
    @GetMapping(params = {"unassignedOnly"})
    public List<UserStoryDto> getAllUnassignedUserStories(@RequestParam Boolean unassignedOnly ) {
        return userStoryService.getAllUserStoriesWithoutDeveloper();
    }

    @GetMapping("/{id}")
    public UserStoryDto getUserStory(@PathVariable UUID id) {
        return userStoryService.getStory(id);
    }

    @PostMapping
    public UserStoryDto createNewUserStory(@RequestBody NewUserStoryDto newUserStory) {
        return userStoryService.addNewUserStory(newUserStory);
    }

    @PutMapping("/{userStoryId}/developers/{developerId}")
    public void assignUserStoryToDeveloper(@PathVariable UUID userStoryId, @PathVariable UUID developerId){
        userStoryService.assignStoryToDeveloper(userStoryId, developerId);
    }

}







//    @GetMapping("/")
//    public String allUserStories(Model model) {
//        model.addAttribute("userStories", userStoryService.getAllUserStories());
//        return "index";
//    }
//
//    @GetMapping("/new-user-story")
//    public String newUserStoryPage(UserStory userStory) {
//        return "new_user_story";
//    }
//
//    @GetMapping("/story/{id}")
//    public String userStoryPage(@PathVariable Long id, Model model) {
////        UserStory userStory = userStoryService.getStory(id);
////        if (userStory.isEmpty()) {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found.");
////        }
//
//        UserStory userStory = userStoryService.getStory(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found."));
//        model.addAttribute("userStory", userStory);
//        return "new_user_story";
//    }
//
//    @PostMapping("/new-user-story")
//    public String createOrUpdateUserStory(@Valid UserStory userStory, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "new_user_story";
//        }
//        userStoryService.createOrUpdateUserStory(userStory);
//        return "redirect:/";
//    }
//
//    @ModelAttribute("dropDownAllStatuses")
//    private List<String> getUserStoriesStatuses() {
//        return Arrays.stream(UserStoryStatus.values())
//                .map(Enum::name)
//                .toList();
//    }
//}
