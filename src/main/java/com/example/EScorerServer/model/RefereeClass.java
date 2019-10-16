package com.example.EScorerServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="referee_classes")
public class RefereeClass {

    @Id
    @Column(name="SHORT_KEY")
    private String shortKey;

    @Column(name="NAME")
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
