package com.codecool.supersprinter3000firstspring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserStory {
    @EqualsAndHashCode.Include
    @Id
    private UUID id = UUID.randomUUID();
    @Version
    private Integer version;
    @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters.")
    private String title;
    @NotBlank(message = "Cannot be empty.")
    private String story;
    private String acceptanceCriteria;
    @NotNull(message = "Cannot be empty.")
    @DecimalMin(value = "0.5", message = "Minimum value is 0.5")
    @DecimalMax(value = "40.0", message = "Maximum value is 40.0")
    private Double estimation;
    private Integer businessValue = 100;
    @Enumerated(EnumType.STRING)
    private UserStoryStatus userStoryStatus;
    @ManyToMany(mappedBy = "userStories")
    private Set<Developer> developers = new HashSet<>();


    public UserStory(String title, String story, String acceptanceCriteria, Double estimation, Integer businessValue, UserStoryStatus userStoryStatus) {
        this.title = title;
        this.story = story;
        this.acceptanceCriteria = acceptanceCriteria;
        this.estimation = estimation;
        this.businessValue = businessValue;
        this.userStoryStatus = userStoryStatus;
    }

    public boolean hasNoDeveloperAssigned() {
        return getDevelopers().isEmpty();
    }

    public void assignDeveloper(Developer developer){
        developers.add(developer);
    }

}
