package com.example.doctoronline.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemAppointmentBinding;

public class ItemAppointmentViewHolder extends RecyclerView.ViewHolder {
    public ItemAppointmentBinding mBinding;

    public ItemAppointmentViewHolder(@NonNull ItemAppointmentBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
