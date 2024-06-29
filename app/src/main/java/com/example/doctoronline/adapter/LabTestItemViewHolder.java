package com.example.doctoronline.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemAvailableTestBinding;

public class LabTestItemViewHolder extends RecyclerView.ViewHolder {
    ItemAvailableTestBinding mBinding;
    public LabTestItemViewHolder(@NonNull ItemAvailableTestBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
