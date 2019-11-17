package com.example.EScorerServer.model;

import com.example.EScorerServer.response.MatchResponse;

import javax.persistence.*;

@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DATE")
    private String date;

    @ManyToOne
    private Team host_team;

    @ManyToOne
    private Team guest_team;

    @Column(name="USER_ID")
    private String userId;

    public Match() {
    }

    public static Match makeFromBody(MatchResponse response, String date)
    {
        Match match = new Match();
        match.setDate(date);
        match.setHost_team(response.getHostTeam());
        match.setGuest_team(response.getGuestTeam());
        match.setUserId(response.getUserId());

        return match;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Team getHost_team() {
        return host_team;
    }

    public void setHost_team(Team host_team) {
        this.host_team = host_team;
    }

    public Team getGuest_team() {
        return guest_team;
    }

    public void setGuest_team(Team guest_team) {
        this.guest_team = guest_team;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
