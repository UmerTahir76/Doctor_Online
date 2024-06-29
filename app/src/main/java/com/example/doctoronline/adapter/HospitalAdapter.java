package com.example.doctoronline.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.databinding.ItemHospitalBinding;
import com.example.doctoronline.model.Hospital;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalItemViewHolder> {

    private List<Hospital> mHospitalList;

    public HospitalAdapter(List<Hospital> mHospitalList) {
        this.mHospitalList = mHospitalList;
    }


    @NonNull
    @Override
    public HospitalItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalItemViewHolder(ItemHospitalBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalItemViewHolder holder, int position) {
        Hospital hospital = mHospitalList.get(position);
        holder.mBinding.hospitalImg.setImageResource(hospital.getImg());
        holder.mBinding.hospitalName.setText(hospital.getName());
        holder.mBinding.city.setText(hospital.getCity());
        holder.mBinding.location.setText(hospital.getLocation());
    }

    @Override
    public int getItemCount() {
        return mHospitalList.size();
    }
}
