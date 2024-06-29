package com.example.doctoronline.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemDoctorBinding;

public class DoctorItemViewHolder extends RecyclerView.ViewHolder {
    ItemDoctorBinding mBinding;
    public DoctorItemViewHolder(@NonNull ItemDoctorBinding mBinding ) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
