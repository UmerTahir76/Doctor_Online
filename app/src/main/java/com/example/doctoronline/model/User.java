package com.example.doctoronline.model;

public class User {

    private String id;
    private String username;
    private String email;
    private String age;
    private String gender;

    private String password;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(String id, String username,  String email, String age , String gender , String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.password = password;
    }

    public User() {
        // Default constructor required for Firestore deserialization
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
