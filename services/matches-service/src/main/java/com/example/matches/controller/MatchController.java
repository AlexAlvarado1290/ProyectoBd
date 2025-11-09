package com.example.matches.controller;

import com.example.matches.model.Goal;
import com.example.matches.model.Match;
import com.example.matches.repo.GoalRepository;
import com.example.matches.repo.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchRepository matchRepository;
    private final GoalRepository goalRepository;

    public MatchController(MatchRepository matchRepository, GoalRepository goalRepository) {
        this.matchRepository = matchRepository;
        this.goalRepository = goalRepository;
    }

    @GetMapping
    public List<Match> listMatches() {
        return matchRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Match createMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    @GetMapping("/goals")
    public List<Goal> listAllGoals() {
        return goalRepository.findAll();
    }

    @GetMapping("/{matchId}/goals")
    public List<Goal> listGoals(@PathVariable Long matchId) {
        return goalRepository.findByMatch_Id(matchId);
    }

    @PostMapping("/{matchId}/goals")
    @ResponseStatus(HttpStatus.CREATED)
    public Goal addGoal(@PathVariable Long matchId, @RequestBody Goal goal) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Partido no encontrado"));
        goal.setMatch(match);
        return goalRepository.save(goal);
    }
}

