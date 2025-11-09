package com.example.players.controller;

import com.example.players.model.Player;
import com.example.players.model.PlayerEmail;
import com.example.players.repo.PlayerEmailRepository;
import com.example.players.repo.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerRepository repo;
    private final PlayerEmailRepository emailRepository;

    public PlayerController(PlayerRepository repo, PlayerEmailRepository emailRepository) {
        this.repo = repo;
        this.emailRepository = emailRepository;
    }

    @GetMapping
    public List<Player> list() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player create(@RequestBody Player p) {
        return repo.save(p);
    }

    @GetMapping("/{playerId}/emails")
    public List<PlayerEmail> listEmails(@PathVariable Long playerId) {
        return emailRepository.findByPlayer_Id(playerId);
    }

    @PostMapping("/{playerId}/emails")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerEmail addEmail(@PathVariable Long playerId, @RequestBody PlayerEmail email) {
        Player player = repo.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado"));
        email.setPlayer(player);
        return emailRepository.save(email);
    }
}
