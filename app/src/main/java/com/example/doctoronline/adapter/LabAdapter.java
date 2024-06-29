package com.example.doctoronline.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.LabClickHandler;
import com.example.doctoronline.databinding.ItemLabBinding;
import com.example.doctoronline.model.Lab;

import java.util.List;

public class LabAdapter extends RecyclerView.Adapter<LabItemViewHolder> {

    private List<Lab> mLabList;
    LabClickHandler labClickHandler;

    public LabAdapter(List<Lab> mLabList , LabClickHandler labClickHandler) {
        this.mLabList = mLabList;
        this.labClickHandler = labClickHandler;
    }

    @NonNull
    @Override
    public LabItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabItemViewHolder(ItemLabBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LabItemViewHolder holder, int position) {
        Lab lab = mLabList.get(position);
        holder.mBinding.imgLab.setImageResource(lab.getImg());
        holder.mBinding.titleLab.setText(lab.getName());
        holder.mBinding.time.setText(lab.getTime());
        holder.mBinding.labAddress.setText(lab.getLocation());
        holder.mBinding.selectBtn.setOnClickListener(v->{
            labClickHandler.selectTest(lab);
        });


    }

    @Override
    public int getItemCount() {
        return mLabList.size();
    }
}
