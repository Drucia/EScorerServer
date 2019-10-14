package com.example.EScorerServer.model;

public class RefereeClass {

    private String shortKey;
    private String name;

    public RefereeClass(){}

    public RefereeClass(String shortKey, String name) {
        this.shortKey = shortKey;
        this.name = name;
    }

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
