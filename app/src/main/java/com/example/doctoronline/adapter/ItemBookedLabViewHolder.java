package com.example.doctoronline.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemLabAppointmentBinding;

public class ItemBookedLabViewHolder extends RecyclerView.ViewHolder {
    ItemLabAppointmentBinding mBinding;
    public ItemBookedLabViewHolder(@NonNull ItemLabAppointmentBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
