package com.daenjel.ilearn.Views;


import java.util.Date;

public class user {
    String username;
    String email;
    Date sessionExpiryDate;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }}

