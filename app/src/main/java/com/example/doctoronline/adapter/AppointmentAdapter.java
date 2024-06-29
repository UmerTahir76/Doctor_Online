package com.example.doctoronline.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemAppointmentBinding;
import com.example.doctoronline.model.Appointment;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<ItemAppointmentViewHolder> {
    private List<Appointment> mAppointmentList;

    public AppointmentAdapter(List<Appointment> mAppointmentList) {
        this.mAppointmentList = mAppointmentList;
    }

    @NonNull
    @Override
    public ItemAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemAppointmentViewHolder(ItemAppointmentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAppointmentViewHolder holder, int position) {
        Appointment appointment = mAppointmentList.get(position);
        holder.mBinding.profileImage.setImageResource(appointment.getImage());
        holder.mBinding.name.setText(appointment.getName());
        holder.mBinding.category.setText(appointment.getCategory());
        holder.mBinding.patientName.setText(appointment.getPatient_Name());
        holder.mBinding.date.setText(appointment.getDate());
        holder.mBinding.time.setText(appointment.getTime());


    }

    @Override
    public int getItemCount() {
        return mAppointmentList.size();
    }
}
