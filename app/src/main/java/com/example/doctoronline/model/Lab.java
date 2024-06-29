package com.example.doctoronline.model;

public class Lab {

    private int img;
    private String name;
    private String time;
    private String location;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Lab() {
    }

    public Lab(int img, String name, String time, String location) {
        this.img = img;
        this.name = name;
        this.time = time;
        this.location = location;
    }
}
