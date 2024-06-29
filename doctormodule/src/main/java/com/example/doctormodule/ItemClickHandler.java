package com.example.doctormodule;

public interface ItemClickHandler {
    void cancel(Appointment appointment , int position);
    void confirm(Appointment appointment , int position);
}
