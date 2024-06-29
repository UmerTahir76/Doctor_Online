package com.example.labapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.labapp.databinding.ActivityLoginBinding;
import com.example.labapp.model.Lab;
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
    ActivityLoginBinding mBinding;
    private FirebaseFirestore mFirestore;
    private List<Lab> mLabsList = new ArrayList<>();
    private boolean userFound = false;
    public static  String labName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mFirestore = FirebaseFirestore.getInstance();
       // registerLab();

        fetchLabsFromFirestore();



        mBinding.loginBtn.setOnClickListener(v -> {
            authenticateLab();
        });

    }

    private void authenticateLab() {
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

            for(Lab lab : mLabsList){
                if((email.equals(lab.getEmail() ))  && (password.equals(lab.getPassword() )) ){
                    labName = lab.getLabName();
                    Intent intent = new Intent(this , MainActivity.class);
                    intent.putExtra("name" , labName);
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

    private void fetchLabsFromFirestore() {

        mLabsList.clear();

        mFirestore.collection("Labs")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Lab lab = documentSnapshot.toObject(Lab.class);
                            mLabsList.add(lab);
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

//    private void registerLab() {
//                mLabsList.add(new Lab("Chugtai Lab","chugtai@gmail.com","abc"));
//                mLabsList.add(new Lab("Niazi Lab","niazi@gmail.com","abc"));
//                mLabsList.add(new Lab("City Lab","city@gmail.com","abc"));
//                mLabsList.add(new Lab("Sadiq Lab","sadiq@gmail.com","abc"));
//        for (Lab lab:  mLabsList) {
//            mFirestore.collection("Labs").add(lab).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentReference> task) {
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(LoginActivity.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//    }
}