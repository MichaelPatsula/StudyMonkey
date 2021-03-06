package com.studymonkey.surveychimp.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studymonkey.surveychimp.security.Security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.NoSuchAlgorithmException;

/**
 * This class stores the data of a user that uses the application.
 *
 * Note: the userID is auto incremented by the database, therefore it is not used
 * when inserting the record into the database.
 */

@Entity(name="Account")
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;

    @Column (
            name="password",
            columnDefinition="bytea"
    )
    @JsonIgnore // no returning password
    private byte[] password;

    public Account() {
    }

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        try {
            this.password = Security.getSHA(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    @JsonIgnore
    public String getHexPassword() {
        return Security.toHexString(password);
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = Security.getSHA(password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "userID='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

