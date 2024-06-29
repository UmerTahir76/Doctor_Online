package com.example.doctoronline.model;

import java.util.List;
import java.util.Map;

public class LabAppointment {
    private String labName;
    private String patientName;
    private String Age;
    private String Gender;
    private int totalBill;

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

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public LabAppointment() {
    }

    public LabAppointment(String labName, String patientName, String age, String gender, int totalBill) {
        this.labName = labName;
        this.patientName = patientName;
        Age = age;
        Gender = gender;
        this.totalBill = totalBill;
    }
}