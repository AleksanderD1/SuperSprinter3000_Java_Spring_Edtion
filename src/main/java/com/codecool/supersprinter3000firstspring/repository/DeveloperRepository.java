package com.codecool.supersprinter3000firstspring.repository;

import com.codecool.supersprinter3000firstspring.entity.Developer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, UUID> {
    @Query("SELECT DISTINCT d from Developer d LEFT JOIN FETCH d.userStories")
    List<Developer> findAllBy();

    @Query("SELECT DISTINCT d from Developer d LEFT JOIN FETCH d.userStories")
    List<Developer> findAllBy(Pageable pageable);

    @Query("SELECT d from Developer d LEFT JOIN FETCH d.userStories WHERE d.id = :id")
    Optional<Developer> findOneById(UUID id);


}
