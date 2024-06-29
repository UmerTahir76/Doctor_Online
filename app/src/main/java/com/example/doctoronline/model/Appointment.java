package com.example.doctoronline.model;

public class Appointment {
    private String id;

    private int image;
    private String patient_Name;

    private String name;
    private String category;
    private String patient_Age;
    private String patient_Gender;

    private String date;
    private String time;

    public String getPatient_Name() {
        return patient_Name;
    }

    public void setPatient_Name(String patient_Name) {
        this.patient_Name = patient_Name;
    }

    public String getPatient_Age() {
        return patient_Age;
    }

    public void setPatient_Age(String patient_Age) {
        this.patient_Age = patient_Age;
    }

    public String getPatient_Gender() {
        return patient_Gender;
    }

    public void setPatient_Gender(String patient_Gender) {
        this.patient_Gender = patient_Gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Appointment(String id , int image, String name, String category, String patient_Age , String patient_Gender , String date, String time , String patient_Name) {
        this.id = id;
        this.image = image;
        this.patient_Name = patient_Name;
        this.name = name;
        this.category = category;
        this.patient_Age = patient_Age;
        this.patient_Gender = patient_Gender;
        this.date = date;
        this.time = time;
    }

    public Appointment() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
