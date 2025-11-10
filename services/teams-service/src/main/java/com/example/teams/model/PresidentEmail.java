package com.example.teams.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "CORREOPRESIDENTE")
public class PresidentEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correo_presidente")
    private Long id;

    @Column(name = "correo_electronico")
    private String email;

    @ManyToOne
    @JoinColumn(name = "dpi")
    @JsonIgnoreProperties({"emails", "team"})
    private President president;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public President getPresident() { return president; }
    public void setPresident(President president) { this.president = president; }
}

