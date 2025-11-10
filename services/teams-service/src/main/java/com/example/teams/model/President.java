package com.example.teams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRESIDENTE")
public class President {

    @Id
    private String dpi;

    @Column(name = "nombre1")
    private String firstName;
    @Column(name = "nombre2")
    private String secondName;
    @Column(name = "nombre3")
    private String thirdName;
    @Column(name = "apellido1")
    private String lastName1;
    @Column(name = "apellido2")
    private String lastName2;
    @Column(name = "fecha_nacimiento")
    private LocalDate birthDate;
    @Column(name = "correo_electronico")
    private String email;
    @Column(name = "anio_elegido")
    private Integer electionYear;

    @OneToOne
    @JoinColumn(name = "codigo_equipo", unique = true)
    @JsonIgnoreProperties("president")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "codigo_municipio")
    @JsonIgnoreProperties({"department", "presidents"})
    private Municipality municipality;

    @JsonIgnore
    @OneToMany(mappedBy = "president", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("president")
    private List<PresidentEmail> emails = new ArrayList<>();

    public String getDpi() { return dpi; }
    public void setDpi(String dpi) { this.dpi = dpi; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSecondName() { return secondName; }
    public void setSecondName(String secondName) { this.secondName = secondName; }

    public String getThirdName() { return thirdName; }
    public void setThirdName(String thirdName) { this.thirdName = thirdName; }

    public String getLastName1() { return lastName1; }
    public void setLastName1(String lastName1) { this.lastName1 = lastName1; }

    public String getLastName2() { return lastName2; }
    public void setLastName2(String lastName2) { this.lastName2 = lastName2; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getElectionYear() { return electionYear; }
    public void setElectionYear(Integer electionYear) { this.electionYear = electionYear; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public List<PresidentEmail> getEmails() { return emails; }
    public void setEmails(List<PresidentEmail> emails) { this.emails = emails; }
}

