package com.example.doctoronline.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemLabBinding;

public class LabItemViewHolder extends RecyclerView.ViewHolder {
    ItemLabBinding mBinding;
    public LabItemViewHolder(@NonNull ItemLabBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
