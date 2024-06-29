package com.example.doctoronline.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctoronline.DoctorClickHandler;
import com.example.doctoronline.databinding.ItemDoctorBinding;
import com.example.doctoronline.model.Doctor;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorItemViewHolder> {
    private List<Doctor> mDoctorsList;

    DoctorClickHandler doctorClickHandler;

    public DoctorAdapter(List<Doctor> mDoctorsList , DoctorClickHandler doctorClickHandler) {
        this.mDoctorsList = mDoctorsList;
        this.doctorClickHandler = doctorClickHandler;
    }

    @NonNull
    @Override
    public DoctorItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorItemViewHolder(ItemDoctorBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorItemViewHolder holder, int position) {
        Doctor doctor = mDoctorsList.get(position);
        holder.mBinding.profileImage.setImageResource(doctor.getImage());
        holder.mBinding.name.setText(doctor.getName());
        holder.mBinding.category.setText(doctor.getCategory());
        holder.mBinding.experience.setText(doctor.getExperience() + "+ years experience");
        holder.mBinding.patients.setText(doctor.getPatients() + "+ patients");
        holder.mBinding.hospital.setText(doctor.getHospital());
        holder.mBinding.time.setText(doctor.getTime());
        holder.mBinding.price.setText( "Rs. " + doctor.getPrice());

        holder.mBinding.viewProfileBtn.setOnClickListener(v->{
            doctorClickHandler.seeDoctorProfile(doctor);
        });

        holder.mBinding.bookAppointmentBtn.setOnClickListener(v->{
            doctorClickHandler.bookAppointment(doctor);
        });
    }

    @Override
    public int getItemCount() {
        return mDoctorsList.size();
    }
}
