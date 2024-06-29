package com.example.labapp;

import com.example.labapp.model.Appointment;

public interface LabTestInterface {
    void cancelAppointment(Appointment appointment);
    void confirmAppointment(Appointment appointment);

}
