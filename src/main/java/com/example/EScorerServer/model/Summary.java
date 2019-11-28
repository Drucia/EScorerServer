package com.example.EScorerServer.model;

import com.example.EScorerServer.response.SummaryResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="summaries")
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "MATCH_ID", unique = true)
    private int matchId;

    @NotNull
    @ManyToOne
    private Team winner;

    @Column(name = "SETS_HOME")
    private int setsHome;

    @Column(name = "SETS_GUEST")
    private int setsGuest;

    @OneToMany(mappedBy = "summary")
    private List<SetInfo> sets;

    public Summary() {
    }

    public Summary(int id, int matchId, @NotNull Team winner, int setsHome, int setsGuest) {
        this.id = id;
        this.matchId = matchId;
        this.winner = winner;
        this.setsHome = setsHome;
        this.setsGuest = setsGuest;
    }

    public static Summary makeFromBody(SummaryResponse response, Match match)
    {
        Summary summary = new Summary();
        summary.setMatchId(match.getId());
        summary.setWinner(response.getWinner());
        summary.setSetsHome(response.getHostSets());
        summary.setSetsGuest(response.getGuestSets());
        return summary;
    }

    public int getId() {
        return id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSetsHome() {
        return setsHome;
    }

    public void setSetsHome(int setsHome) {
        this.setsHome = setsHome;
    }

    public int getSetsGuest() {
        return setsGuest;
    }

    public void setSetsGuest(int setsGuest) {
        this.setsGuest = setsGuest;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public List<SetInfo> getSets() {
        return sets;
    }

    public void setSets(List<SetInfo> sets) {
        this.sets = sets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return id == summary.id &&
                matchId == summary.matchId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId);
    }
}
