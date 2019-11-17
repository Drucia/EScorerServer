package com.example.EScorerServer.response;

import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.Team;

public class MatchResponse {
    private int id;
    private Team hostTeam;
    private Team guestTeam;
    private String name;
    private String userId;

    public MatchResponse() {
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
}
