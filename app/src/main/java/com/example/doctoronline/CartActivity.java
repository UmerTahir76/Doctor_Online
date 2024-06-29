package com.example.doctoronline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.doctoronline.adapter.LabTestAdapter;
import com.example.doctoronline.auth.LoginActivity;
import com.example.doctoronline.databinding.ActivityCartBinding;
import com.example.doctoronline.model.Lab;
import com.example.doctoronline.model.LabAppointment;
import com.example.doctoronline.model.LabTest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    public ActivityCartBinding mBinding;

    public LabTestAdapter mLabAdapter;
    public FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        db = FirebaseFirestore.getInstance();

        List<LabTest> selectedLabTests = LabDashboard.mSelectedTests;
        Intent intent = getIntent();
        String labName = intent.getStringExtra("name");
        int totalBill = intent.getIntExtra("bill", 0);
        String patientName = intent.getStringExtra("patient_name");
        String patientAge = intent.getStringExtra("patient_age");
        String patientGender = intent.getStringExtra("patient_gender");
        mBinding.bill.setText(String.valueOf(totalBill));

        mBinding.labTestsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mLabAdapter = new LabTestAdapter(selectedLabTests);
        mBinding.labTestsRecyclerview.setAdapter(mLabAdapter);

        mBinding.confirmBookingBtn.setOnClickListener(v->{
            LabAppointment labAppointment = new LabAppointment(labName,patientName,patientAge,patientGender,totalBill);

            db.collection("LabAppointments").add(labAppointment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    LabDashboard.orderConfirmed = true;
                    Toast.makeText(CartActivity.this, "Your Booking Has Been Confirmed", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CartActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}