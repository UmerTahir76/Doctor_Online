package com.example.doctoronline.model;

public class LabTest {
    private String name;
    private int price;
    private int realPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(int realPrice) {
        this.realPrice = realPrice;
    }

    public LabTest() {

    }

    public LabTest(String name, int price, int realPrice) {
        this.name = name;
        this.price = price;
        this.realPrice = realPrice;
    }
}
