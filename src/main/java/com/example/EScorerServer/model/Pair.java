package com.example.EScorerServer.model;

import java.util.List;

public class Pair<Team, Player> {
    private Team team;
    private List<Player> players;

    public Pair() {
    }

    public Pair(Team team, List<Player> players) {
        this.team = team;
        this.players = players;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
