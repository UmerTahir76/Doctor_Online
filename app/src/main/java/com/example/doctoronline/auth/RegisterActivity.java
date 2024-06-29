package com.example.doctoronline.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doctoronline.UserDashboard;
import com.example.doctoronline.databinding.ActivityRegisterBinding;
import com.example.doctoronline.model.User;
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

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding mBinding;
    private FirebaseFirestore mDB;
    private List<User> mUserList= new ArrayList<>();
    private boolean userAlreadyExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mDB = FirebaseFirestore.getInstance();
        fetchUsersFromFirestore();

        mBinding.registerBtn.setOnClickListener(v->{
            String username = mBinding.username.getText().toString();
            String email = mBinding.email.getText().toString();
            String age = mBinding.age.getText().toString();
            String gender = mBinding.gender.getText().toString();
            String password = mBinding.password.getText().toString();
            String confirmPassword = mBinding.confirmPassword.getText().toString();
            userExists(email);
            if(userAlreadyExists){
                Toast.makeText(this, "Already Registered With This Email", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(username ,email ,age , gender ,password ,confirmPassword);

            }
        });
    }

    private void userExists(String email) {
        userAlreadyExists = false;
        for(User user : mUserList){
            if(email.equals(user.getEmail() ) ){
                userAlreadyExists = true;
                break;
            }
        }
    }
    private void fetchUsersFromFirestore() {
        mUserList.clear();
        mDB.collection("Users")
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

    private void registerUser(String username , String email , String age , String gender , String password , String confirmPassword) {

        if (username.isEmpty()){
            mBinding.username.setError("Enter Username !");
            mBinding.username.requestFocus();
        } else if (email.isEmpty()){
            mBinding.email.setError("Enter Email !");
            mBinding.email.requestFocus();
        }  else if (age.isEmpty()){
            mBinding.age.setError("Enter Age !");
            mBinding.age.requestFocus();
        }  else if (gender.isEmpty()){
            mBinding.gender.setError("Enter Gender !");
            mBinding.gender.requestFocus();
        }
        else if (password.isEmpty()){
            mBinding.password.setError("Enter Password !");
            mBinding.password.requestFocus();
        } else if (confirmPassword.isEmpty()){
            mBinding.confirmPassword.setError("Confirm Password !");
            mBinding.confirmPassword.requestFocus();
        } else{
            if(password.equals(confirmPassword)){
                User user = new User("" , username , email ,age , gender, password);
                mDB.collection("Users").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            DocumentReference document = task.getResult();
                            if(document != null){
                                String documentId = document.getId();
                                mDB.collection("Users").document(documentId).update("id" , documentId);
                                startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
                                finish();
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else{
                mBinding.confirmPassword.setError("Password Didn't Matched");
                mBinding.confirmPassword.requestFocus();
            }
        }
    }
}