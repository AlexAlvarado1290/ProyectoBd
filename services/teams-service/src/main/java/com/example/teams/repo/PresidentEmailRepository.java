package com.example.teams.repo;

import com.example.teams.model.PresidentEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresidentEmailRepository extends JpaRepository<PresidentEmail, Long> {
    List<PresidentEmail> findByPresident_Dpi(String dpi);
}

