package com.example.teams.controller;

import com.example.teams.model.Municipality;
import com.example.teams.model.President;
import com.example.teams.model.PresidentEmail;
import com.example.teams.model.Team;
import com.example.teams.repo.MunicipalityRepository;
import com.example.teams.repo.PresidentEmailRepository;
import com.example.teams.repo.PresidentRepository;
import com.example.teams.repo.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/presidents")
public class PresidentController {

    private final PresidentRepository presidentRepository;
    private final PresidentEmailRepository emailRepository;
    private final TeamRepository teamRepository;
    private final MunicipalityRepository municipalityRepository;

    public PresidentController(PresidentRepository presidentRepository,
                               PresidentEmailRepository emailRepository,
                               TeamRepository teamRepository,
                               MunicipalityRepository municipalityRepository) {
        this.presidentRepository = presidentRepository;
        this.emailRepository = emailRepository;
        this.teamRepository = teamRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @GetMapping
    public List<President> listPresidents() {
        return presidentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public President create(@RequestBody Map<String, Object> payload) {
        President president = new President();
        applyPresidentPayload(president, payload, true);
        return presidentRepository.save(president);
    }

    @PutMapping("/{dpi}")
    public President update(@PathVariable String dpi, @RequestBody Map<String, Object> payload) {
        President existing = presidentRepository.findById(dpi)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Presidente no encontrado"));
        applyPresidentPayload(existing, payload, false);
        return presidentRepository.save(existing);
    }

    @DeleteMapping("/{dpi}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String dpi) {
        if (!presidentRepository.existsById(dpi)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Presidente no encontrado");
        }
        presidentRepository.deleteById(dpi);
    }

    @GetMapping("/{dpi}/emails")
    public List<PresidentEmail> listEmails(@PathVariable String dpi) {
        return emailRepository.findByPresident_Dpi(dpi);
    }

    @PostMapping("/{dpi}/emails")
    @ResponseStatus(HttpStatus.CREATED)
    public PresidentEmail addEmail(@PathVariable String dpi, @RequestBody PresidentEmail email) {
        President president = presidentRepository.findById(dpi)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Presidente no encontrado"));
        email.setPresident(president);
        return emailRepository.save(email);
    }

    @PutMapping("/{dpi}/emails/{emailId}")
    public PresidentEmail updateEmail(@PathVariable String dpi,
                                      @PathVariable Long emailId,
                                      @RequestBody PresidentEmail payload) {
        PresidentEmail existing = emailRepository.findById(emailId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo de presidente no encontrado"));
        if (!existing.getPresident().getDpi().equals(dpi)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo no pertenece al presidente indicado");
        }
        existing.setEmail(payload.getEmail());
        return emailRepository.save(existing);
    }

    @DeleteMapping("/{dpi}/emails/{emailId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmail(@PathVariable String dpi, @PathVariable Long emailId) {
        PresidentEmail email = emailRepository.findById(emailId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Correo de presidente no encontrado"));
        if (!email.getPresident().getDpi().equals(dpi)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo no pertenece al presidente indicado");
        }
        emailRepository.delete(email);
    }

    private void applyPresidentPayload(President president, Map<String, Object> payload, boolean isCreate) {
        if (isCreate) {
            String dpi = stringValue(payload.get("dpi"));
            if (dpi == null || dpi.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "DPI requerido");
            }
            president.setDpi(dpi);
        }
        president.setFirstName(stringValue(payload.get("firstName")));
        president.setSecondName(stringValue(payload.get("secondName")));
        president.setThirdName(stringValue(payload.get("thirdName")));
        president.setLastName1(stringValue(payload.get("lastName1")));
        president.setLastName2(stringValue(payload.get("lastName2")));
        president.setEmail(stringValue(payload.get("email")));
        president.setMunicipality(resolveMunicipality(payload.get("municipalityId"), payload.get("municipality")));
        president.setBirthDate(dateValue(payload.get("birthDate")));
        president.setElectionYear(intValue(payload.get("electionYear")));
        president.setTeam(resolveTeam(payload.get("teamId"), payload.get("team")));
    }

    private Team resolveTeam(Object teamIdValue, Object teamObj) {
        Long teamId = longValue(teamIdValue);
        if (teamId == null && teamObj instanceof Map<?, ?> map) {
            teamId = longValue(map.get("id"));
        }
        if (teamId == null) {
            return null;
        }
        Long finalTeamId = teamId;
        return teamRepository.findById(finalTeamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado"));
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

    private Integer intValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number number) {
            return number.intValue();
        }
        return Integer.parseInt(value.toString());
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
