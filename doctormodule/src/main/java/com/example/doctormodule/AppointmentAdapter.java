package com.example.doctormodule;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctormodule.databinding.ItemAppointmentBinding;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<ItemAppointmentViewHolder> {
    private List<Appointment> appointments;
    private ItemClickHandler itemClickHandler;

    public AppointmentAdapter(List<Appointment> appointments , ItemClickHandler itemClickHandler) {
        this.appointments = appointments;
        this.itemClickHandler = itemClickHandler;
    }

    @NonNull
    @Override
    public ItemAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemAppointmentViewHolder(ItemAppointmentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAppointmentViewHolder holder, int position) {

        Appointment appointment = appointments.get(position);
        holder.mBinding.patientName.setText(appointment.getPatient_Name());
        holder.mBinding.patientAge.setText(appointment.getPatient_Age());
        holder.mBinding.patientGender.setText(appointment.getPatient_Gender());
        holder.mBinding.time.setText(appointment.getTime());

        holder.mBinding.cancelButton.setOnClickListener(v->{
            itemClickHandler.cancel(appointment , position);
        });
        holder.mBinding.confirmButton.setOnClickListener(v->{
            itemClickHandler.confirm(appointment , position);
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
}
