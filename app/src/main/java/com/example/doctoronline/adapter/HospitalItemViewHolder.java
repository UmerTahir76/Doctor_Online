package com.example.doctoronline.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemHospitalBinding;

public class HospitalItemViewHolder extends RecyclerView.ViewHolder {
    ItemHospitalBinding mBinding;
    public HospitalItemViewHolder(@NonNull ItemHospitalBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
