package com.example.doctoronline.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doctoronline.auth.LoginActivity;
import com.example.doctoronline.auth.RegisterActivity;
import com.example.doctoronline.databinding.ActivityLandingPageBinding;

public class LandingPage extends AppCompatActivity {
    private ActivityLandingPageBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        mBinding.registerBtn.setOnClickListener(v->{
            startActivity(new Intent(this , RegisterActivity.class));
        });

        mBinding.loginBtn.setOnClickListener(v->{
            startActivity(new Intent(this , LoginActivity.class));

        });
    }
}