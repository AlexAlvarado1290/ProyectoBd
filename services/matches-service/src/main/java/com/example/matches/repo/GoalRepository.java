package com.example.matches.repo;

import com.example.matches.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByMatch_Id(Long matchId);
}

