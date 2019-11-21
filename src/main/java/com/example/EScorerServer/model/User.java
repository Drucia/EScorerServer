package com.example.EScorerServer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="ID")
    private String id;

    @NotBlank
    @Column(name="NAME")
    private String name;

    @NotBlank
    @Column(name="SURNAME")
    private String surname;

    @Column(name="IS_REFEREE")
    private boolean isReferee;

    @Column(name="CERTIFICATE")
    private String certificate;

    @Column(name="REFEREE_CLASS")
    private String refereeClass;

    public User(){}

    public User(String id, String name, String surname, boolean isReferee, String certificate,
                String refereeClass) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isReferee = isReferee;
        this.certificate = certificate;
        this.refereeClass = refereeClass;
    }

    public User(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public boolean getIsReferee() {
        return isReferee;
    }

    public void setIsReferee(boolean referee) {
        isReferee = referee;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getRefereeClass() {
        return refereeClass;
    }

    public void setRefereeClass(String refereeClass) {
        this.refereeClass = refereeClass;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
