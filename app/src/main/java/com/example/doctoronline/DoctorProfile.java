package com.example.doctoronline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doctoronline.databinding.ActivityDoctorProfileBinding;

public class DoctorProfile extends AppCompatActivity {
    ActivityDoctorProfileBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDoctorProfileBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        String patientsNo = intent.getIntExtra("Patients" , 0) + "";
        String experienceYears = intent.getIntExtra("Experience" , 0) + "";
        String doctorFee = intent.getIntExtra("Price" , 0) + "";

        mBinding.profileImage.setImageResource(intent.getIntExtra("Image" , 0));
        mBinding.doctorName.setText(intent.getStringExtra("Name" ));
        mBinding.category.setText(intent.getStringExtra("Category" ));
        mBinding.patientNumbers.setText(patientsNo);
        mBinding.patientNumbersExp.setText(experienceYears);
        mBinding.hospitalName.setText(intent.getStringExtra("Hospital" ));
        mBinding.time.setText(intent.getStringExtra("Time" ));
        mBinding.doctorFees.setText(doctorFee);

        mBinding.bookAppointBtn.setOnClickListener(v->{
            startActivity(new Intent(this , BookDoctorAppointment.class));
        });
        mBinding.backIcon.setOnClickListener(v->{
            finish();
        });







    }
}