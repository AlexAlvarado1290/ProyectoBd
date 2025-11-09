package com.example.teams.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String stadium;
    private Integer capacity;
    private Integer foundationYear;
    private String department;

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

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public President getPresident() { return president; }
    public void setPresident(President president) { this.president = president; }
}
