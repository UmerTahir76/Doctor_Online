package com.example.doctoronline.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemLabAppointmentBinding;
import com.example.doctoronline.model.BookedLabAppointment;

import java.util.List;

public class BookedLabAdapter extends RecyclerView.Adapter<ItemBookedLabViewHolder> {
    List<BookedLabAppointment> bookedLabAppointmentsList;

    public BookedLabAdapter(List<BookedLabAppointment> bookedLabAppointmentsList) {
        this.bookedLabAppointmentsList = bookedLabAppointmentsList;
    }

    @NonNull
    @Override
    public ItemBookedLabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemBookedLabViewHolder(ItemLabAppointmentBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBookedLabViewHolder holder, int position) {
        BookedLabAppointment appointment = bookedLabAppointmentsList.get(position);
        holder.mBinding.labName.setText(appointment.getLabName());
        holder.mBinding.bill.setText(String.valueOf(appointment.getTotalBill()));
    }

    @Override
    public int getItemCount() {
        return bookedLabAppointmentsList.size();
    }
}
