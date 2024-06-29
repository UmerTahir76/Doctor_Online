package com.example.doctoronline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.doctoronline.adapter.DoctorAdapter;
import com.example.doctoronline.databinding.ActivityCategorizedDoctorsBinding;
import com.example.doctoronline.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class CategorizedDoctors extends AppCompatActivity implements DoctorClickHandler {
    ActivityCategorizedDoctorsBinding mBinding;
    private List<Doctor> mDoctorsList = new ArrayList<>();
    private DoctorAdapter mDoctorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCategorizedDoctorsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        mBinding.backIcon.setOnClickListener(v->{
            finish();
        });

        mBinding.category.setText(category);

        mBinding.doctorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDoctorsList.add(new Doctor(R.drawable.male_doctor , "Dr. Ahmad Hassan" ,"abc", category , 7,105,"Khizar Hospital", "10:00 A.M - 5:00 P.M", 1200));
        mDoctorsList.add(new Doctor(R.drawable.male_doctor , "Dr. Ali Nawaz" ,"abc", category , 5,176,"Fatima Hospital", "8:00 A.M - 2:00 P.M", 900));
        mDoctorsList.add(new Doctor(R.drawable.female_doctor , "Dr. Sana Riaz" ,"abc", category , 8,205,"Civil Hospital", "10:00 A.M - 5:00 P.M", 100));
        mDoctorsList.add(new Doctor(R.drawable.male_doctor , "Dr. Jaffar Mahdi" ,"abc", category , 4,100,"Mubarak Hospital", "2:00 P.M - 5:00 P.M", 1500));
        mDoctorsList.add(new Doctor(R.drawable.female_doctor , "Dr. Zainab Razzaq" ,"abc", category , 6,150,"Sadiq Hospital", "5:00 P.M - 11:00 P.M", 800));

        mDoctorAdapter = new DoctorAdapter(mDoctorsList , this);

        mBinding.doctorsRecyclerView.setAdapter(mDoctorAdapter);

    }

    @Override
    public void bookAppointment(Doctor doctor) {
        Intent intent = new Intent(this , BookDoctorAppointment.class);
        intent.putExtra("Image" , doctor.getImage());
        intent.putExtra("Name" , doctor.getName());
        intent.putExtra("Category" , doctor.getCategory());
        startActivity(intent);
    }

    @Override
    public void seeDoctorProfile(Doctor doctor) {
        Intent intent = new Intent(this , DoctorProfile.class);
        intent.putExtra("Image" , doctor.getImage());
        intent.putExtra("Name" , doctor.getName());
        intent.putExtra("Category" , doctor.getCategory());
        intent.putExtra("Patients" , doctor.getPatients());
        intent.putExtra("Experience" , doctor.getExperience());
        intent.putExtra("Hospital" , doctor.getHospital());
        intent.putExtra("Time" , doctor.getTime());
        intent.putExtra("Price" , doctor.getPrice());
        startActivity(intent);




    }

    @Override
    public void addMenuProvider(@NonNull MenuProvider provider, @NonNull LifecycleOwner owner, @NonNull Lifecycle.State state) {

    }
}