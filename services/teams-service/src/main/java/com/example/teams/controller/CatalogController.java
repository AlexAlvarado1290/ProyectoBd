package com.example.teams.controller;

import com.example.teams.model.Department;
import com.example.teams.model.Municipality;
import com.example.teams.repo.DepartmentRepository;
import com.example.teams.repo.MunicipalityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final DepartmentRepository departmentRepository;
    private final MunicipalityRepository municipalityRepository;

    public CatalogController(DepartmentRepository departmentRepository,
                             MunicipalityRepository municipalityRepository) {
        this.departmentRepository = departmentRepository;
        this.municipalityRepository = municipalityRepository;
    }

    @GetMapping("/departments")
    public List<Department> listDepartments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/municipalities")
    public List<Municipality> listMunicipalities(@RequestParam(required = false) Long departmentId) {
        if (departmentId != null) {
            return municipalityRepository.findByDepartment_Id(departmentId);
        }
        return municipalityRepository.findAll();
    }

    @GetMapping("/departments/{departmentId}/municipalities")
    public List<Municipality> listMunicipalitiesByDepartment(@PathVariable Long departmentId) {
        return municipalityRepository.findByDepartment_Id(departmentId);
    }

    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.name());
        return departmentRepository.save(department);
    }

    @PutMapping("/departments/{departmentId}")
    public Department updateDepartment(@PathVariable Long departmentId,
                                       @RequestBody DepartmentRequest request) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado"));
        department.setName(request.name());
        return departmentRepository.save(department);
    }

    @DeleteMapping("/departments/{departmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado");
        }
        departmentRepository.deleteById(departmentId);
    }

    @PostMapping("/municipalities")
    @ResponseStatus(HttpStatus.CREATED)
    public Municipality createMunicipality(@RequestBody MunicipalityRequest request) {
        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departamento no válido"));
        Municipality municipality = new Municipality();
        municipality.setName(request.name());
        municipality.setDepartment(department);
        return municipalityRepository.save(municipality);
    }

    @PutMapping("/municipalities/{municipalityId}")
    public Municipality updateMunicipality(@PathVariable Long municipalityId,
                                           @RequestBody MunicipalityRequest request) {
        Municipality municipality = municipalityRepository.findById(municipalityId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado"));
        Department department = departmentRepository.findById(request.departmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departamento no válido"));
        municipality.setName(request.name());
        municipality.setDepartment(department);
        return municipalityRepository.save(municipality);
    }

    @DeleteMapping("/municipalities/{municipalityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMunicipality(@PathVariable Long municipalityId) {
        if (!municipalityRepository.existsById(municipalityId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Municipio no encontrado");
        }
        municipalityRepository.deleteById(municipalityId);
    }

    private record DepartmentRequest(String name) {
    }

    private record MunicipalityRequest(String name, Long departmentId) {
    }
}

