package com.example.EScorerServer.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @NotEmpty
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @ManyToOne
    private Team team;

    public Player(){}

    public Player(String name, String surname, Team team) {
        this.name = name;
        this.surname = surname;
        this.team = team;
    }

    public Player(int id, @NotEmpty String name, @NotEmpty String surname, @NotNull Team team) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.team = team;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Player) obj).id;
    }
}
