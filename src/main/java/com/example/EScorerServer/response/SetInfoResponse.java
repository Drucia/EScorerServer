package com.example.EScorerServer.response;

import com.example.EScorerServer.model.SetInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetInfoResponse {
    private int shiftsHome;
    private int shiftsGuest;
    private int timesHome;
    private int timesGust;
    private int pointsHome;
    private int pointsGuest;
    private int set;
    private int time;

    public SetInfoResponse() {
    }

    public SetInfoResponse(int shiftsHome, int shiftsGuest, int timesHome, int timesGust, int pointsHome,
                           int pointsGuest, int set, int time) {
        this.shiftsHome = shiftsHome;
        this.shiftsGuest = shiftsGuest;
        this.timesHome = timesHome;
        this.timesGust = timesGust;
        this.pointsHome = pointsHome;
        this.pointsGuest = pointsGuest;
        this.set = set;
        this.time = time;
    }

    public static Map<Integer, SetInfoResponse> makeFromBody(List<SetInfo> sets) {
        Map<Integer, SetInfoResponse> response = new HashMap<>();
        for (SetInfo info : sets)
        {
            SetInfoResponse responseInfo = new SetInfoResponse();
            responseInfo.setShiftsHome(info.getShiftsHome());
            responseInfo.setShiftsGuest(info.getShiftsGuest());
            responseInfo.setTimesHome(info.getTimesHome());
            responseInfo.setTimesGust(info.getTimeGuest());
            responseInfo.setPointsHome(info.getPointsHome());
            responseInfo.setPointsGuest(info.getPointsGuest());
            responseInfo.setSet(info.getSet());
            responseInfo.setTime(info.getTime());
            response.put(responseInfo.getSet(), responseInfo);
        }
        return response;
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

    public int getTimesGust() {
        return timesGust;
    }

    public void setTimesGust(int timesGust) {
        this.timesGust = timesGust;
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
}
