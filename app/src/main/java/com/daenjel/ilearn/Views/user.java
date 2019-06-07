package com.daenjel.ilearn.Views;


import java.util.Date;

public class user {
    String name;
    String email;
    Date sessionExpiryDate;

    public void setname(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }

    public String getname() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }}

