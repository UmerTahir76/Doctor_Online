package com.example.labapp.model;

import java.util.List;
import java.util.Map;

public class Lab {
    private String labName;
    private String email;
    private String password;

    public Lab(String labName, String email, String password) {
        this.labName = labName;
        this.email = email;
        this.password = password;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Lab() {
    }
}
