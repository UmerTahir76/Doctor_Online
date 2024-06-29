package com.example.labapp.model;

public class Appointment {
    String age;
    String gender;
    String labName;

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

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public Appointment() {
    }

    public Appointment(String age, String gender, String labName, String patientName, int totalBill) {
        this.age = age;
        this.gender = gender;
        this.labName = labName;
        this.patientName = patientName;
        this.totalBill = totalBill;
    }

    String patientName;
    int totalBill;

}
