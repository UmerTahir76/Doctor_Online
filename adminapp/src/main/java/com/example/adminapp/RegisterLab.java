package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.adminapp.databinding.ActivityRegisterLabBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterLab extends AppCompatActivity {
    ActivityRegisterLabBinding mBinding;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterLabBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFirestore = FirebaseFirestore.getInstance();


        mBinding.registerBtn.setOnClickListener(v->{
            String email = mBinding.email.getText().toString();
            String name = mBinding.name.getText().toString();
            String password = mBinding.password.getText().toString();

            Lab lab = new Lab(email,name,password);
            mFirestore.collection("Labs").add(lab).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(RegisterLab.this, " Registered " , Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterLab.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}