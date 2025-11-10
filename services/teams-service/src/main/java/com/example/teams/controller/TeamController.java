package com.example.teams.controller;

import com.example.teams.model.Department;
import com.example.teams.model.Team;
import com.example.teams.repo.DepartmentRepository;
import com.example.teams.repo.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository repo;
    private final DepartmentRepository departmentRepository;

    public TeamController(TeamRepository repo, DepartmentRepository departmentRepository) {
        this.repo = repo;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public List<Team> list() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team create(@RequestBody Map<String, Object> payload) {
        Team team = new Team();
        applyTeamPayload(team, payload);
        return repo.save(team);
    }

    @PutMapping("/{id}")
    public Team update(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        Team team = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado"));
        applyTeamPayload(team, payload);
        return repo.save(team);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado");
        }
        repo.deleteById(id);
    }

    private void applyTeamPayload(Team team, Map<String, Object> payload) {
        team.setName(stringValue(payload.get("name")));
        team.setStadium(stringValue(payload.get("stadium")));
        team.setCapacity(intValue(payload.get("capacity")));
        team.setFoundationYear(intValue(payload.get("foundationYear")));
        Department department = resolveDepartment(payload.get("departmentId"), payload.get("department"));
        team.setDepartment(department);
    }

    private Department resolveDepartment(Object departmentIdValue, Object departmentObj) {
        Long departmentId = longValue(departmentIdValue);
        if (departmentId == null && departmentObj instanceof Map<?, ?> map) {
            departmentId = longValue(map.get("id"));
        }
        if (departmentId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departamento requerido");
        }
        Long finalDepartmentId = departmentId;
        return departmentRepository.findById(finalDepartmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado"));
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
}
