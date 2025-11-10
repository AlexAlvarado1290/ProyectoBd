package com.example.players.controller;

import com.example.players.model.Municipality;
import com.example.players.model.Player;
import com.example.players.model.PlayerEmail;
import com.example.players.repo.MunicipalityRepository;
import com.example.players.repo.PlayerEmailRepository;
import com.example.players.repo.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerRepository repo;
    private final PlayerEmailRepository emailRepository;
    private final MunicipalityRepository municipalityRepository;

    public PlayerController(PlayerRepository repo,
                            PlayerEmailRepository emailRepository,
                            MunicipalityRepository municipalityRepository) {
        this.repo = repo;
        this.emailRepository = emailRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @GetMapping
    public List<Player> list() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player create(@RequestBody Map<String, Object> payload) {
        Player player = new Player();
        applyPlayerPayload(player, payload);
        return repo.save(player);
    }

    @PutMapping("/{playerId}")
    public Player update(@PathVariable Long playerId, @RequestBody Map<String, Object> payload) {
        Player existing = repo.findById(playerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador no encontrado"));
        applyPlayerPayload(existing, payload);
        return repo.save(existing);
    }

    @DeleteMapping("/{playerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long playerId) {
        if (!repo.existsById(playerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador no encontrado");
        }
        repo.deleteById(playerId);
    }

    @GetMapping("/{playerId}/emails")
    public List<PlayerEmail> listEmails(@PathVariable Long playerId) {
        return emailRepository.findByPlayer_Id(playerId);
    }

    @PostMapping("/{playerId}/emails")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerEmail addEmail(@PathVariable Long playerId, @RequestBody PlayerEmail email) {
        Player player = repo.findById(playerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jugador no encontrado"));
        email.setPlayer(player);
        return emailRepository.save(email);
    }

    @PutMapping("/{playerId}/emails/{emailId}")
    public PlayerEmail updateEmail(@PathVariable Long playerId,
                                   @PathVariable Long emailId,
                                   @RequestBody PlayerEmail payload) {
        PlayerEmail existing = emailRepository.findById(emailId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo de jugador no encontrado"));
        if (!existing.getPlayer().getId().equals(playerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo no pertenece al jugador indicado");
        }
        existing.setEmail(payload.getEmail());
        return emailRepository.save(existing);
    }

    @DeleteMapping("/{playerId}/emails/{emailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmail(@PathVariable Long playerId, @PathVariable Long emailId) {
        PlayerEmail email = emailRepository.findById(emailId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo de jugador no encontrado"));
        if (!email.getPlayer().getId().equals(playerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo no pertenece al jugador indicado");
        }
        emailRepository.delete(email);
    }

    private void applyPlayerPayload(Player player, Map<String, Object> payload) {
        player.setFirstName(stringValue(payload.get("firstName")));
        player.setSecondName(stringValue(payload.get("secondName")));
        player.setLastName1(stringValue(payload.get("lastName1")));
        player.setLastName2(stringValue(payload.get("lastName2")));
        player.setEmail(stringValue(payload.get("email")));
        player.setBirthDate(dateValue(payload.get("birthDate")));
        player.setPosition(stringValue(payload.get("position")));
        player.setTeamId(longValue(payload.get("teamId")));
        player.setMunicipality(resolveMunicipality(payload.get("municipalityId"), payload.get("municipality")));
    }

    private Municipality resolveMunicipality(Object municipalityIdValue, Object municipalityObj) {
        Long municipalityId = longValue(municipalityIdValue);
        if (municipalityId == null && municipalityObj instanceof Map<?, ?> map) {
            municipalityId = longValue(map.get("id"));
        }
        if (municipalityId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Municipio requerido");
        }
        Long finalMunicipalityId = municipalityId;
        return municipalityRepository.findById(finalMunicipalityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado"));
    }

    private String stringValue(Object value) {
        return value == null ? null : value.toString();
    }

    private Long longValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number number) {
            return number.longValue();
        }
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private LocalDate dateValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDate localDate) {
            return localDate;
        }
        return LocalDate.parse(value.toString());
    }
}
