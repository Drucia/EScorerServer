package com.example.EScorerServer.response;
import com.example.EScorerServer.model.Match;
import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SummaryResponse {
    private int id;
    private String date;
    private MatchResponse match;
    private Team winner;
    private int hostSets;
    private int guestSets;
    private Map<Integer, SetInfoResponse> sets;

    public SummaryResponse() {
    }

    public SummaryResponse(String date, MatchResponse match, Team winner, int hostSets, int guestSets,
                           Map<Integer, SetInfoResponse> sets) {
        this.date = date;
        this.match = match;
        this.winner = winner;
        this.hostSets = hostSets;
        this.guestSets = guestSets;
        this.sets = sets;
    }

    public static SummaryResponse makeFromBody(Summary summary, Match match, List<SetInfo> setsInfo) {
        SummaryResponse response = new SummaryResponse();
        response.setId(summary.getId());
        response.setDate(match.getDate());
        response.setMatch(MatchResponse.makeFromBody(match));
        response.setWinner(summary.getWinner());
        response.setHostSets(summary.getSetsHome());
        response.setGuestSets(summary.getSetsGuest());
        response.setSets(SetInfoResponse.makeFromBody(setsInfo));
        return response;
    }

    public static List<SummaryResponse> makeFromBody(List<Summary> summaries, List<Match> matches) {
        List<SummaryResponse> responses = new ArrayList<>();
        for (int i = 0; i < summaries.size(); i++) {
            Summary summary = summaries.get(i);
            responses.add(SummaryResponse.makeFromBody(summary, matches.get(i), summary.getSets()));
        }
        return responses;
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

    public MatchResponse getMatch() {
        return match;
    }

    public void setMatch(MatchResponse match) {
        this.match = match;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public int getHostSets() {
        return hostSets;
    }

    public void setHostSets(int hostSets) {
        this.hostSets = hostSets;
    }

    public int getGuestSets() {
        return guestSets;
    }

    public void setGuestSets(int guestSets) {
        this.guestSets = guestSets;
    }

    public Map<Integer, SetInfoResponse> getSets() {
        return sets;
    }

    public void setSets(Map<Integer, SetInfoResponse> sets) {
        this.sets = sets;
    }
}
