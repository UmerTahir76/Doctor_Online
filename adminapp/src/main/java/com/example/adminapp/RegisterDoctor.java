package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.adminapp.databinding.ActivityRegisterDoctorBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterDoctor extends AppCompatActivity {
    ActivityRegisterDoctorBinding mBinding;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterDoctorBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFirestore = FirebaseFirestore.getInstance();


        mBinding.registerBtn.setOnClickListener(v->{
            String name = mBinding.name.getText().toString();
            String password = mBinding.password.getText().toString();
            String email = mBinding.email.getText().toString();
            String category = mBinding.category.getText().toString();
            int experience = Integer.parseInt(mBinding.experience.getText().toString());
            int patients = Integer.parseInt(mBinding.patients.getText().toString());
            String hospital = mBinding.hospital.getText().toString();
            String time = mBinding.time.getText().toString();
            int price = Integer.parseInt(mBinding.price.getText().toString());

            Doc doctor = new Doc("",R.drawable.male_doctor,name,email,password,category,experience,patients,hospital,time,price);

            mFirestore.collection("Doctors").add(doctor).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if(task.isSuccessful()){
                        DocumentReference document = task.getResult();
                        if(document != null){
                            String documentId = document.getId();
                            mFirestore.collection("Doctors").document(documentId).update("id" , documentId);
                            Toast.makeText(RegisterDoctor.this, "Registered", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterDoctor.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}