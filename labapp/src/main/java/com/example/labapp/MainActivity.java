package com.example.labapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.labapp.adapter.LabAppointmentAdapter;
import com.example.labapp.databinding.ActivityMainBinding;
import com.example.labapp.model.Appointment;
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

public class MainActivity extends AppCompatActivity implements LabTestInterface{

    private ActivityMainBinding mBinding;
    private FirebaseFirestore db;
    private LabAppointmentAdapter mAdapter;
    private List<Appointment> mAppointmentList = new ArrayList<>();
   public String labName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        labName = intent.getStringExtra("name");

        mBinding.name.setText(labName);
        db = FirebaseFirestore.getInstance();
        fetchAppointments();

    }

    private void fetchAppointments() {
        db.collection("LabAppointments").whereEqualTo("labName" ,LoginActivity.labName )
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Appointment appointment = documentSnapshot.toObject(Appointment.class);
                            mAppointmentList.add(appointment);
                        }
                        mBinding.pendingCounter.setText(mAppointmentList.size()+"");
                        show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                    }
                });
    }

    private void show() {
        mBinding.labAppointmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new LabAppointmentAdapter(mAppointmentList , this);
        mBinding.labAppointmentRecyclerView.setAdapter(mAdapter);
    }



    @Override
    public void cancelAppointment(Appointment appointment) {

    }

    @Override
    public void confirmAppointment(Appointment appointment) {
        db.collection("ConfirmedLabAppointments").add(appointment).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(MainActivity.this, "Confirmed!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void addMenuProvider(@NonNull MenuProvider provider, @NonNull LifecycleOwner owner, @NonNull Lifecycle.State state) {

    }
}