package com.example.players.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String secondName;
    private String lastName1;
    private String lastName2;
    private String email;
    private String municipality;
    private LocalDate birthDate;
    private String position;
    private Long teamId;

    @JsonIgnore
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("player")
    private List<PlayerEmail> emails = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSecondName() { return secondName; }
    public void setSecondName(String secondName) { this.secondName = secondName; }

    public String getLastName1() { return lastName1; }
    public void setLastName1(String lastName1) { this.lastName1 = lastName1; }

    public String getLastName2() { return lastName2; }
    public void setLastName2(String lastName2) { this.lastName2 = lastName2; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMunicipality() { return municipality; }
    public void setMunicipality(String municipality) { this.municipality = municipality; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    public List<PlayerEmail> getEmails() { return emails; }
    public void setEmails(List<PlayerEmail> emails) { this.emails = emails; }
}
