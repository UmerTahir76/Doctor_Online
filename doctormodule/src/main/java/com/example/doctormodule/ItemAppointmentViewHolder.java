package com.example.doctormodule;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctormodule.databinding.ItemAppointmentBinding;

public class ItemAppointmentViewHolder extends RecyclerView.ViewHolder {
    ItemAppointmentBinding mBinding;
    public ItemAppointmentViewHolder(@NonNull ItemAppointmentBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
