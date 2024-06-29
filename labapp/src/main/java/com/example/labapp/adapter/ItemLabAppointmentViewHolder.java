package com.example.labapp.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labapp.databinding.ItemAppointmentBinding;


public class ItemLabAppointmentViewHolder extends RecyclerView.ViewHolder {
     ItemAppointmentBinding mBinding;
    public ItemLabAppointmentViewHolder(@NonNull ItemAppointmentBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
