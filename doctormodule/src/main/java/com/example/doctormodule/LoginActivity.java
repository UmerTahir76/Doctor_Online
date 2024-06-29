package com.example.doctormodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doctormodule.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding mBinding;
    private FirebaseFirestore mFirestore;
    private List<Doctor> mDoctorsList = new ArrayList<>();
    private boolean userFound = false;
    public static  String doctorName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mFirestore = FirebaseFirestore.getInstance();
        //registerDoctor();

        fetchDoctorsFromFirestore();



        mBinding.loginBtn.setOnClickListener(v -> {
            authenticateDoctor();
        });
    }

    private void authenticateDoctor() {
        userFound = false;
        String email = mBinding.email.getText().toString();
        String password = mBinding.password.getText().toString();

        if (email.isEmpty()) {
            mBinding.email.setError("Enter Email ");
            mBinding.email.requestFocus();
        } else if (password.isEmpty()) {
            mBinding.password.setError("Enter Password ");
            mBinding.password.requestFocus();
        } else {

            for(Doctor doctor : mDoctorsList){
                if((email.equals(doctor.getEmail() ))  && (password.equals(doctor.getPassword() )) ){
                    doctorName = doctor.getName();
                    Intent intent = new Intent(this , MainActivity.class);
                    intent.putExtra("name" , doctor.getName());
                    startActivity(intent);
                    userFound = true;
                    break;
                }
            }
            if(!userFound){
                Toast.makeText(this, "User Not Found ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void fetchDoctorsFromFirestore() {
        mDoctorsList.clear();

        mFirestore.collection("Doctors")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Doctor doctor = documentSnapshot.toObject(Doctor.class);
                            mDoctorsList.add(doctor);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                    }
                });
    }

//    private void registerDoctor() {
//
//        mAddDoctorsList.add(new Doctor("",R.drawable.male_doctor , "Dr. Ahmad Hassan" , "Dentist" , 7 , 105 , "Khizar Hospital" , "10:00 A.M - 5:00 P.M" , 1200 , "ahmad@gmail.com" , "abc"));
//        mAddDoctorsList.add(new Doctor("",R.drawable.male_doctor , "Dr. Ali Nawaz" , "Dentist" , 5 , 176 , "Fatima Hospital" , "8: A.M - 2:00 P.M" , 900 , "ali@gmail.com" , "abc"));
//        mAddDoctorsList.add(new Doctor("",R.drawable.female_doctor , "Dr. Sana Riaz" , "Dentist" , 8 , 205 , "Civil Hospital" , "10:00 A.M - 5:00 P.M" , 100 , "sana@gmail.com" , "abc"));
//        mAddDoctorsList.add(new Doctor("",R.drawable.male_doctor , "Dr. Jaffar Mahdi" , "Dentist" , 4 , 100 , "Mubarak Hospital" , "2:00 A.M - 5:00 P.M" , 1500 , "jaffar@gmail.com" , "abc"));
//        mAddDoctorsList.add(new Doctor("",R.drawable.female_doctor , "Dr. Zainab Razzaq" , "Dentist" , 6 , 150 , "Sadiq Hospital" , "5:00 A.M - 11:00 P.M" , 800 , "zainab@gmail.com" , "abc"));
//
//
////                Doctor doctor = new Doctor("",R.drawable.male_doctor , "Dr. Ahmad Hassan" , "Dentist" , 7 , 120 , "Fatima Hospital" , "10: A.M - 2:00 P.M" , 1200 , "ahmad@gmail.com" , "abc");
//        for (Doctor doctor: mAddDoctorsList) {
//            mFirestore.collection("Doctors").add(doctor).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentReference> task) {
//                    if(task.isSuccessful()){
//                        DocumentReference document = task.getResult();
//                        if(document != null){
//                            String documentId = document.getId();
//                            mFirestore.collection("Doctors").document(documentId).update("id" , documentId);
//                        }
//                    }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(LoginActivity.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//
//    }
}
