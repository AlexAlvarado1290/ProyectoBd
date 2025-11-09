package com.example.teams.controller;

import com.example.teams.model.President;
import com.example.teams.model.PresidentEmail;
import com.example.teams.model.Team;
import com.example.teams.repo.PresidentEmailRepository;
import com.example.teams.repo.PresidentRepository;
import com.example.teams.repo.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presidents")
public class PresidentController {

    private final PresidentRepository presidentRepository;
    private final PresidentEmailRepository emailRepository;
    private final TeamRepository teamRepository;

    public PresidentController(PresidentRepository presidentRepository,
                               PresidentEmailRepository emailRepository,
                               TeamRepository teamRepository) {
        this.presidentRepository = presidentRepository;
        this.emailRepository = emailRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<President> listPresidents() {
        return presidentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public President create(@RequestBody President president) {
        if (president.getTeam() != null && president.getTeam().getId() != null) {
            Team team = teamRepository.findById(president.getTeam().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));
            president.setTeam(team);
        } else {
            president.setTeam(null);
        }
        return presidentRepository.save(president);
    }

    @GetMapping("/{dpi}/emails")
    public List<PresidentEmail> listEmails(@PathVariable String dpi) {
        return emailRepository.findByPresident_Dpi(dpi);
    }

    @PostMapping("/{dpi}/emails")
    @ResponseStatus(HttpStatus.CREATED)
    public PresidentEmail addEmail(@PathVariable String dpi, @RequestBody PresidentEmail email) {
        President president = presidentRepository.findById(dpi)
                .orElseThrow(() -> new IllegalArgumentException("Presidente no encontrado"));
        email.setPresident(president);
        return emailRepository.save(email);
    }
}

