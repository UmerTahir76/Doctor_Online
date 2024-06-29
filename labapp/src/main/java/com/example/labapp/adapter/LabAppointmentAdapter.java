package com.example.labapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labapp.LabTestInterface;
import com.example.labapp.databinding.ItemAppointmentBinding;
import com.example.labapp.model.Appointment;
import com.example.labapp.model.Lab;

import java.util.List;

public class LabAppointmentAdapter extends RecyclerView.Adapter<ItemLabAppointmentViewHolder> {
    private List<Appointment> labAppointments;
    private LabTestInterface labTestInterface;

    public LabAppointmentAdapter(List<Appointment> labAppointments, LabTestInterface labTestInterface) {
        this.labAppointments = labAppointments;
        this.labTestInterface = labTestInterface;
    }

    @NonNull
    @Override
    public ItemLabAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemLabAppointmentViewHolder(ItemAppointmentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLabAppointmentViewHolder holder, int position) {

        Appointment appointment = labAppointments.get(position);
        holder.mBinding.patientName.setText(appointment.getPatientName());
        holder.mBinding.patientAge.setText(appointment.getAge());
        holder.mBinding.patientGender.setText(appointment.getGender());
        holder.mBinding.totalBill.setText(String.valueOf(appointment.getTotalBill()));
        holder.mBinding.confirmButton.setOnClickListener(v->{
            labTestInterface.confirmAppointment(appointment);
        });
    }

    @Override
    public int getItemCount() {
        return labAppointments.size();
    }
}
