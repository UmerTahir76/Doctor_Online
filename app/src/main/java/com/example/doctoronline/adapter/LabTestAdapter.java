package com.example.doctoronline.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.LabClickHandler;
import com.example.doctoronline.databinding.ItemAvailableTestBinding;
import com.example.doctoronline.model.LabTest;

import java.util.List;

public class LabTestAdapter extends RecyclerView.Adapter<LabTestItemViewHolder> {

    List<LabTest> mLabTestList;
    LabClickHandler labClickHandler;

    public LabTestAdapter(List<LabTest> mLabTestList) {
        this.mLabTestList = mLabTestList;
    }

    public LabTestAdapter(List<LabTest> mLabTestList , LabClickHandler labClickHandler) {
        this.mLabTestList = mLabTestList;
        this.labClickHandler = labClickHandler;
    }

    @NonNull
    @Override
    public LabTestItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabTestItemViewHolder(ItemAvailableTestBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LabTestItemViewHolder holder, int position) {
        LabTest labTest = mLabTestList.get(position);
        holder.mBinding.testName.setText(labTest.getName());
        holder.mBinding.testPrice.setText("Rs. " + labTest.getPrice());
        holder.mBinding.realPrice.setText("Rs. " + labTest.getRealPrice());
        holder.mBinding.addBtn.setOnClickListener(v->{
            labClickHandler.addToCart(labTest);
        });


    }

    @Override
    public int getItemCount() {
        return mLabTestList.size();
    }
}
