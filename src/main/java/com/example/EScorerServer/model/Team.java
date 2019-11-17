package com.example.EScorerServer.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="SHORT_NAME")
    private String shortName;

    @Column(name="USER_ID")
    private String userId;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    @OneToMany(mappedBy = "host_team")
    private Set<Match> matchesAsHostTeam;

    @OneToMany(mappedBy = "guest_team")
    private Set<Match> matchesAsGuestTeam;

    @OneToMany(mappedBy = "winner")
    private Set<Summary> matchesWon;

    public Team(){}

    public Team(String name, String shortName, String userId) {
        this.name = name;
        this.shortName = shortName;
        this.userId = userId;
    }

    public Team(int id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Team) obj).id;
    }
}
