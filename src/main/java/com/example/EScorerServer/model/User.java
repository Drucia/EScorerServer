package com.example.EScorerServer.model;

public class User {
    private String id;

    private String name;

    private String surname;

    private String fullName;

    private Boolean isReferee;

    private String certificate;

    public User(){}

    public User(String id, String name, String surname, String fullName, Boolean isReferee, String certificate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.fullName = fullName;
        this.isReferee = isReferee;
        this.certificate = certificate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getReferee() {
        return isReferee;
    }

    public void setReferee(Boolean referee) {
        isReferee = referee;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
