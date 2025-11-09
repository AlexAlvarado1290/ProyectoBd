package com.example.teams.repo;

import com.example.teams.model.President;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresidentRepository extends JpaRepository<President, String> {
}

