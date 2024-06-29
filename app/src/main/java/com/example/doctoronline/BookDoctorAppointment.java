package com.example.doctoronline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.doctoronline.auth.LoginActivity;
import com.example.doctoronline.auth.RegisterActivity;
import com.example.doctoronline.databinding.ActivityBookDoctorAppointmentBinding;
import com.example.doctoronline.model.Appointment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class BookDoctorAppointment extends AppCompatActivity {
    public static List<Appointment> appointmentList = new ArrayList<>();
    private FirebaseFirestore mFirestore;

    ActivityBookDoctorAppointmentBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBookDoctorAppointmentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mFirestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        int image = intent.getIntExtra("Image" , 0);
        String name = intent.getStringExtra("Name");
        String category = intent.getStringExtra("Category");

        mBinding.crossIcon.setOnClickListener(v->{
            finish();
        });

        mBinding.bookAppointmentBtn.setOnClickListener(v->{
            Toast.makeText(this, "Please Wait ", Toast.LENGTH_SHORT).show();

            String patient_name = UserDashboard.username;
            int day = mBinding.datepicker.getDayOfMonth();
            int month = mBinding.datepicker.getMonth()+1;
            int year = mBinding.datepicker.getYear();
            String date = day + "-" + month + "-" + year;


            Appointment appointment = new Appointment("",image , name, category , LoginActivity.presentUser.getAge(), LoginActivity.presentUser.getGender(),  date , "2:30 P.M - 4:30 P.M" , patient_name);
            mFirestore.collection("Appointments").add(appointment).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if(task.isSuccessful()){
                        DocumentReference document = task.getResult();
                        if(document != null){
                            String documentId = document.getId();
                            mFirestore.collection("Appointments").document(documentId).update("id" , documentId);
                            Toast.makeText(BookDoctorAppointment.this, "Booked " + day + "-" + month + "-" + year, Toast.LENGTH_SHORT).show();
                            new Handler(Looper.getMainLooper()).postDelayed(()->{
                                finish();
                            },2000);
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BookDoctorAppointment.this, " Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

//            appointmentList.add(new Appointment(image , name , category , date , "2:30 P.M - 4:30 P.M"));
        });

    }
}