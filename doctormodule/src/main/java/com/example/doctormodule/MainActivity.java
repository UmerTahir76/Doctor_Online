package com.example.doctormodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.doctormodule.databinding.ActivityMainBinding;
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

public class MainActivity extends AppCompatActivity implements ItemClickHandler{
    ActivityMainBinding mBinding;
    List<Appointment> mAppointmentList = new ArrayList<>();
    AppointmentAdapter mAppointmentAdapter;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mFirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mBinding.name.setText(name);

        mBinding.backIcon.setOnClickListener(v->{
            finish();
        });

        fetchAppointments();


    }

    private void fetchAppointments() {
        mFirestore.collection("Appointments").whereEqualTo("name" , LoginActivity.doctorName)
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
        mBinding.appointmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAppointmentAdapter = new AppointmentAdapter(mAppointmentList , this);
        mBinding.appointmentRecyclerView.setAdapter(mAppointmentAdapter);
    }

    @Override
    public void cancel(Appointment appointment , int position) {
        removeFromDb(appointment , position);
//        String appointmentId = appointment.getId();
//        mFirestore.collection("Appointments")
//                .whereEqualTo("id", appointmentId)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            mFirestore.collection("Appointments")
//                                    .document(documentSnapshot.getId())
//                                    .delete()
//                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
//                                            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
//                                        }
//                                    })
//                                    .addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(MainActivity.this, "err " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                    }
//                });
//        mAppointmentList.remove(appointment);
//        mAppointmentAdapter.notifyDataSetChanged();

    }
    @Override
    public void confirm(Appointment appointment , int position) {
        Appointment appoint = new Appointment("",appointment.getImage() ,appointment.getName(), appointment.getCategory() , appointment.getPatient_Age() , appointment.getPatient_Gender() ,  appointment.getDate() , appointment.getTime() , appointment.getPatient_Name());
        mFirestore.collection("ConfirmedAppointments").add(appoint).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    DocumentReference document = task.getResult();
                    if(document != null){
                        String documentId = document.getId();
                        mFirestore.collection("ConfirmedAppointments").document(documentId).update("id" , documentId);
                        Toast.makeText(MainActivity.this, " Confirmed " , Toast.LENGTH_SHORT).show();
                        removeFromDb(appointment , position);
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void removeFromDb(Appointment appointment , int position){
        String appointmentId = appointment.getId();
        mFirestore.collection("Appointments")
                .whereEqualTo("id", appointmentId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            mFirestore.collection("Appointments")
                                    .document(documentSnapshot.getId())
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this, "err " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        mAppointmentList.remove(appointment);
        mAppointmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void addMenuProvider(@NonNull MenuProvider provider, @NonNull LifecycleOwner owner, @NonNull Lifecycle.State state) {

    }
}