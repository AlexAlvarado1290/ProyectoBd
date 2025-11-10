package com.example.teams.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "EQUIPO")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_equipo")
    private Long id;

    @Column(name = "nombre")
    private String name;
    @Column(name = "estadio")
    private String stadium;
    @Column(name = "aforo")
    private Integer capacity;
    @Column(name = "fundacion")
    private Integer foundationYear;

    @ManyToOne
    @JoinColumn(name = "codigo_departamento")
    @JsonIgnoreProperties({"teams", "municipalities"})
    private Department department;

    @OneToOne(mappedBy = "team")
    @JsonIgnoreProperties(value = "team", allowSetters = true)
    private President president;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStadium() { return stadium; }
    public void setStadium(String stadium) { this.stadium = stadium; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Integer getFoundationYear() { return foundationYear; }
    public void setFoundationYear(Integer foundationYear) { this.foundationYear = foundationYear; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public President getPresident() { return president; }
    public void setPresident(President president) { this.president = president; }
}
