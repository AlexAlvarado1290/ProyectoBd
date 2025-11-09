package com.example.matches.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playerId;
    private Integer minute;
    private String description;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonIgnoreProperties({"goals"})
    private Match match;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public Integer getMinute() { return minute; }
    public void setMinute(Integer minute) { this.minute = minute; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
}

