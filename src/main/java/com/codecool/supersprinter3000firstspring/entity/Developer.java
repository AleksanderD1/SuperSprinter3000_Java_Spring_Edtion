package com.codecool.supersprinter3000firstspring.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Developer {
    @Id
    private UUID id = UUID.randomUUID();
    @Version
    private Integer version;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Include
    @Column(unique = true)
    private String email;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "developer_user_story",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "user_story_id")
    )
    private Set<UserStory> userStories = new HashSet<>();

    public Developer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void clearDeveloperUserStories() {
       userStories.clear();
    }


    public void addUserStory(UserStory userStory) {
        userStories.add(userStory);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getUserStoriesCount() {
        return getUserStories().size();
    }
}
