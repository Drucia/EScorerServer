package com.example.EScorerServer.model;

import com.example.EScorerServer.response.MatchResponse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotBlank
    @Column(name = "DATE")
    private String date;

    @NotNull
    @ManyToOne
    private Team host_team;

    @NotNull
    @ManyToOne
    private Team guest_team;

    @NotBlank
    @Column(name="USER_ID")
    private String userId;

    public Match() {
    }

    public static Match makeFromBody(MatchResponse response)
    {
        Match match = new Match();
        match.setDate(response.getDate());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id &&
                date.equals(match.date) &&
                userId.equals(match.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, userId);
    }
}
