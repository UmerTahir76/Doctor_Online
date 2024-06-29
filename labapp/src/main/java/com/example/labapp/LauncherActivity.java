package com.example.labapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labapp.databinding.ActivityLauncherBinding;


public class LauncherActivity extends AppCompatActivity {
    private ActivityLauncherBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLauncherBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        new Handler(Looper.getMainLooper()).postDelayed(()->{
            startActivity(new Intent(this , LoginActivity.class));
            finish();
        },2000);
    }
}