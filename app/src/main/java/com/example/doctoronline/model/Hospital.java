package com.example.doctoronline.model;

public class Hospital {
    private int img;
    private String name;
    private String city;
    private String location;

    public Hospital(int img, String name, String city, String location) {
        this.img = img;
        this.name = name;
        this.city = city;
        this.location = location;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
