package com.example.doctoronline;

import com.example.doctoronline.model.Lab;
import com.example.doctoronline.model.LabTest;

public interface LabClickHandler {
    public void selectTest(Lab lab);
    public void addToCart(LabTest labTest);
}
