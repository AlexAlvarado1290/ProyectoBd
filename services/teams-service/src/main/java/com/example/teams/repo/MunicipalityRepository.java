package com.example.teams.repo;

import com.example.teams.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    List<Municipality> findByDepartment_Id(Long departmentId);
}

