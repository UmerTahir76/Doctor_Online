package com.example.doctoronline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doctoronline.databinding.ActivityFindDoctorsBinding;

public class FindDoctors extends AppCompatActivity {
    private ActivityFindDoctorsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFindDoctorsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.backIcon.setOnClickListener(v->{
            finish();
        });

        mBinding.dentistBtn.setOnClickListener(v->{
            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Dentists" );
            startActivity(intent);

        });

        mBinding.gastroenterologistBtn.setOnClickListener(v->{
            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Gastroenterologist" );
            startActivity(intent);

        });

        mBinding.psychatristBtn.setOnClickListener(v->{

            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Psychatrist" );
            startActivity(intent);
        });

        mBinding.audiologistBtn.setOnClickListener(v->{

            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Audiologist" );
            startActivity(intent);
        });


        mBinding.dermatologistBtn.setOnClickListener(v->{

            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Dermatologist" );
            startActivity(intent);
        });

        mBinding.urologistBtn.setOnClickListener(v->{
            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Urologist" );
            startActivity(intent);

        });

        mBinding.cardiologistBtn.setOnClickListener(v->{
            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Cardiologist" );
            startActivity(intent);

        });

        mBinding.orthopedicBtn.setOnClickListener(v->{
            Intent intent = new Intent(this , CategorizedDoctors.class);
            intent.putExtra("category" , "Orthopedic" );
            startActivity(intent);
        });




    }
}