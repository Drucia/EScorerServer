package com.example.EScorerServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;

    @Column(name="IS_REFEREE")
    private Boolean isReferee;

    @Column(name="CERTIFICATE")
    private String certificate;

    @Column(name="REFEREE_CLASS")
    private String refereeClass;

    public User(){}

    public User(String id, String name, String surname, Boolean isReferee, String certificate,
                String refereeClass) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isReferee = isReferee;
        this.certificate = certificate;
        this.refereeClass = refereeClass;
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
        return name + " " + surname;
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
