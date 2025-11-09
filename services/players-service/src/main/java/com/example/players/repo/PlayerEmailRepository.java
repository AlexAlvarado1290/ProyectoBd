package com.example.players.repo;

import com.example.players.model.PlayerEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerEmailRepository extends JpaRepository<PlayerEmail, Long> {
    List<PlayerEmail> findByPlayer_Id(Long playerId);
}

