package com.example.EScorerServer.model;

import com.example.EScorerServer.response.SetInfoResponse;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "sets_info")
public class SetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    private Summary summary;

    @Column(name = "SET_NUMBER")
    private int set;

    @Column(name = "TIME")
    private int time;

    @Column(name = "SHIFTS_HOME")
    private int shiftsHome;

    @Column(name = "SHIFTS_GUEST")
    private int shiftsGuest;

    @Column(name = "TIMES_HOME")
    private int timesHome;

    @Column(name = "TIMES_GUEST")
    private int timeGuest;

    @Column(name = "POINTS_HOME")
    private int pointsHome;

    @Column(name = "POINTS_GUEST")
    private int pointsGuest;

    public SetInfo() {
    }

    public static SetInfo makeFromBody(SetInfoResponse response, Summary summary)
    {
        SetInfo setInfo = new SetInfo();
        setInfo.setSummary(summary);
        setInfo.setSet(response.getSet());
        setInfo.setTime(response.getTime());
        setInfo.setShiftsHome(response.getShiftsHome());
        setInfo.setShiftsGuest(response.getShiftsGuest());
        setInfo.setTimesHome(response.getTimesHome());
        setInfo.setTimeGuest(response.getTimesGust());
        setInfo.setPointsHome(response.getPointsHome());
        setInfo.setPointsGuest(response.getPointsGuest());
        return setInfo;
    }

    public static List<SetInfo> makeFromBody(Map<Integer, SetInfoResponse> sets, Summary summary)
    {
        return sets.values().stream().map(response -> makeFromBody(response, summary)).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getShiftsHome() {
        return shiftsHome;
    }

    public void setShiftsHome(int shiftsHome) {
        this.shiftsHome = shiftsHome;
    }

    public int getShiftsGuest() {
        return shiftsGuest;
    }

    public void setShiftsGuest(int shiftsGuest) {
        this.shiftsGuest = shiftsGuest;
    }

    public int getTimesHome() {
        return timesHome;
    }

    public void setTimesHome(int timesHome) {
        this.timesHome = timesHome;
    }

    public int getTimeGuest() {
        return timeGuest;
    }

    public void setTimeGuest(int timeGuest) {
        this.timeGuest = timeGuest;
    }

    public int getPointsHome() {
        return pointsHome;
    }

    public void setPointsHome(int pointsHome) {
        this.pointsHome = pointsHome;
    }

    public int getPointsGuest() {
        return pointsGuest;
    }

    public void setPointsGuest(int pointsGuest) {
        this.pointsGuest = pointsGuest;
    }
}
