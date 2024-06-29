package com.example.doctoronline.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doctoronline.UserDashboard;
import com.example.doctoronline.databinding.ActivityLoginBinding;
import com.example.doctoronline.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding mBinding;
    private FirebaseFirestore mFirestore;
    private List<User> mUserList;
    public static User presentUser;
    private boolean userFound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mFirestore = FirebaseFirestore.getInstance();
        mUserList = new ArrayList<>();

        fetchUsersFromFirestore();



        mBinding.loginBtn.setOnClickListener(v -> {
            authenticateUser();
        });
    }

    private void authenticateUser() {
        String email = mBinding.email.getText().toString();
        String password = mBinding.password.getText().toString();

        if (email.isEmpty()) {
            mBinding.email.setError("Enter Email ");
            mBinding.email.requestFocus();
        } else if (password.isEmpty()) {
            mBinding.password.setError("Enter Password ");
            mBinding.password.requestFocus();
        } else {

            for(User user : mUserList){
                if((email.equals(user.getEmail() ))  && (password.equals(user.getPassword() )) ){
                    presentUser = user;
                    Intent intent = new Intent(this , UserDashboard.class);
                    intent.putExtra("name" , user.getUsername());
                    startActivity(intent);
                    finish();
                    userFound = true;
                    break;
                }
            }
            if(!userFound){
                Toast.makeText(this, "User Not Found ", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void fetchUsersFromFirestore() {
        mFirestore.collection("Users")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            User user = documentSnapshot.toObject(User.class);
                            mUserList.add(user);
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

}