package com.example.teams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MUNICIPIO")
public class Municipality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_municipio")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @ManyToOne
    @JoinColumn(name = "codigo_departamento")
    @JsonIgnoreProperties({"municipalities", "teams"})
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "municipality")
    @JsonIgnoreProperties("municipality")
    private List<President> presidents = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<President> getPresidents() {
        return presidents;
    }

    public void setPresidents(List<President> presidents) {
        this.presidents = presidents;
    }
}

