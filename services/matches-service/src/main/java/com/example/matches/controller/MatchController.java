package com.example.matches.controller;

import com.example.matches.model.Goal;
import com.example.matches.model.Match;
import com.example.matches.repo.GoalRepository;
import com.example.matches.repo.MatchRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/{matchId}")
    public Match updateMatch(@PathVariable Long matchId, @RequestBody Match payload) {
        Match existing = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partido no encontrado"));
        existing.setDate(payload.getDate());
        existing.setHomeGoals(payload.getHomeGoals());
        existing.setAwayGoals(payload.getAwayGoals());
        existing.setHomeTeamId(payload.getHomeTeamId());
        existing.setAwayTeamId(payload.getAwayTeamId());
        return matchRepository.save(existing);
    }

    @DeleteMapping("/{matchId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@PathVariable Long matchId) {
        if (!matchRepository.existsById(matchId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Partido no encontrado");
        }
        matchRepository.deleteById(matchId);
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Partido no encontrado"));
        goal.setMatch(match);
        return goalRepository.save(goal);
    }

    @PutMapping("/{matchId}/goals/{goalId}")
    public Goal updateGoal(@PathVariable Long matchId,
                           @PathVariable Long goalId,
                           @RequestBody Goal payload) {
        Goal existing = goalRepository.findById(goalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gol no encontrado"));
        if (!existing.getMatch().getId().equals(matchId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El gol no pertenece al partido indicado");
        }
        existing.setPlayerId(payload.getPlayerId());
        existing.setMinute(payload.getMinute());
        existing.setDescription(payload.getDescription());
        return goalRepository.save(existing);
    }

    @DeleteMapping("/{matchId}/goals/{goalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGoal(@PathVariable Long matchId, @PathVariable Long goalId) {
        Goal existing = goalRepository.findById(goalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gol no encontrado"));
        if (!existing.getMatch().getId().equals(matchId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El gol no pertenece al partido indicado");
        }
        goalRepository.delete(existing);
    }
}

