package com.example.doctoronline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.doctoronline.adapter.HospitalAdapter;
import com.example.doctoronline.databinding.ActivityUserDashboardBinding;
import com.example.doctoronline.model.Hospital;

import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends AppCompatActivity {
    ActivityUserDashboardBinding mBinding;
    private List<Hospital> mHospitalList;
    private HospitalAdapter mAdapter;
    public static String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        mBinding.para2.setText(username);

        mHospitalList = new ArrayList<>();
        addHospitals();

        mBinding.findDoctorsBtn.setOnClickListener(v->{
            startActivity(new Intent(this , FindDoctors.class));
        });

        mBinding.findLabsBtn.setOnClickListener(v->{
            startActivity(new Intent(this , FindLabs.class));
        });

        mBinding.dashedView.setOnClickListener(v->{
            startActivity(new Intent(this , BookedAppointments.class));
        });


    }

    private void addHospitals() {
        mHospitalList.add(new Hospital(R.drawable.hospital1 , "Niazi Medical Complex" , "Sargodha" , "Club Road"));
        mHospitalList.add(new Hospital(R.drawable.hospital2 , "Rai Heart Clinic" , "Sargodha" , "Lahore Road"));
        mHospitalList.add(new Hospital(R.drawable.hospital1 , "Mubarak Hospital" , "Sargodha" , "Fatima Jinnah Road"));
        mHospitalList.add(new Hospital(R.drawable.hospital2 , "Fatima Hospital" , "Sargodha" , "University Road"));
        mHospitalList.add(new Hospital(R.drawable.hospital1 , "Sadiq Hospital" , "Sargodha" , "Zafarullah Road"));

        LinearLayoutManager llm = new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false);
        mBinding.hospitalRecyclerView.setLayoutManager(llm);

        mAdapter = new HospitalAdapter(mHospitalList);
        mBinding.hospitalRecyclerView.setAdapter(mAdapter);
    }
}