package com.codecool.supersprinter3000firstspring.repository;


import com.codecool.supersprinter3000firstspring.entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, UUID> {
    List<UserStory> findAllByOrderById();

    @Query("SELECT DISTINCT us from UserStory us LEFT JOIN FETCH us.developers")
    List<UserStory> findAllBy();

//    @Query("SELECT us FROM UserStory us ORDER BY :arg")
//    List<UserStory> findAllBySomething(String arg);
}
