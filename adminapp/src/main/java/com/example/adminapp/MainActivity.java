package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.adminapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.registerDoctorBtn.setOnClickListener(v->{
            startActivity(new Intent(this , RegisterDoctor.class ));
        });

        mBinding.registerLabBtn.setOnClickListener(v->{
            startActivity(new Intent(this , RegisterLab.class ));
        });
    }
}