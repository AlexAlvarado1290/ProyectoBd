package com.example.teams.controller;

import com.example.teams.model.Team;
import com.example.teams.repo.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository repo;

    public TeamController(TeamRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Team> list() {
        return repo.findAll();
    }

    @PostMapping
    public Team create(@RequestBody Team t) {
        return repo.save(t);
    }
}
