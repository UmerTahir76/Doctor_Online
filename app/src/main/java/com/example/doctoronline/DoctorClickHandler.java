package com.example.doctoronline;

import com.example.doctoronline.model.Doctor;

public interface DoctorClickHandler {
    void bookAppointment(Doctor doctor);
    void seeDoctorProfile(Doctor doctor);
}
