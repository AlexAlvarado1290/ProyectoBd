package com.example.teams.controller;

import com.example.teams.model.Department;
import com.example.teams.model.Municipality;
import com.example.teams.repo.DepartmentRepository;
import com.example.teams.repo.MunicipalityRepository;
import org.springframework.web.bind.annotation.*;

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
}

