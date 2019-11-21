package com.example.EScorerServer.response;

import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.Team;

import java.util.Objects;

public class MatchResponse {
    private int id;
    private Team hostTeam;
    private Team guestTeam;
    private String name;
    private String userId;
    private String date;

    public MatchResponse() {
    }

    public MatchResponse(Team hostTeam, Team guestTeam, String name, String userId, String date) {
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        this.name = name;
        this.userId = userId;
        this.date = date;
    }

    public static MatchResponse makeFromBody(Match match) {
        MatchResponse response = new MatchResponse();
        response.setId(match.getId());
        response.setHostTeam(match.getHost_team());
        response.setGuestTeam(match.getGuest_team());
        response.setName(match.getGuest_team().getName() + " vs " + match.getHost_team().getName());
        response.setUserId(match.getUserId());
        return response;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(Team hostTeam) {
        this.hostTeam = hostTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        MatchResponse that = (MatchResponse) o;
        return id == that.id &&
                userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}
