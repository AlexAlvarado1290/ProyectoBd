package com.example.players.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "CORREOJUGADOR")
public class PlayerEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_correo_jugador")
    private Long id;

    @Column(name = "correo")
    private String email;

    @ManyToOne
    @JoinColumn(name = "codigo_jugador")
    @JsonIgnoreProperties({"emails"})
    private Player player;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
}

