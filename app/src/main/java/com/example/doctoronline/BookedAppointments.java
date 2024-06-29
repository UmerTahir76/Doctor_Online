package com.example.doctoronline;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.doctoronline.adapter.AppointmentAdapter;
import com.example.doctoronline.adapter.BookedLabAdapter;
import com.example.doctoronline.databinding.ActivityBookDoctorAppointmentBinding;
import com.example.doctoronline.databinding.ActivityBookedAppointmentsBinding;
import com.example.doctoronline.model.Appointment;
import com.example.doctoronline.model.BookedLabAppointment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BookedAppointments extends AppCompatActivity {
    ActivityBookedAppointmentsBinding mBinding;
    private List<Appointment> mAppointmentList = new ArrayList<>();
    private List<BookedLabAppointment> mLabAppointmentList = new ArrayList<>();

    private FirebaseFirestore mFirestore;

    private AppointmentAdapter mAppointmentAdapter;
    private BookedLabAdapter mLabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityBookedAppointmentsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mFirestore = FirebaseFirestore.getInstance();
        fetchAppointments();


        mBinding.backIcon.setOnClickListener(v->{
            finish();
        });

    }
    private void fetchAppointments() {
        mFirestore.collection("ConfirmedAppointments").whereEqualTo("patient_Name" , UserDashboard.username)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Appointment appointment = documentSnapshot.toObject(Appointment.class);
                            mAppointmentList.add(appointment);
                        }
                        show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                    }
                });
        mFirestore.collection("ConfirmedLabAppointments").whereEqualTo("patientName" , UserDashboard.username)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            BookedLabAppointment appointment = documentSnapshot.toObject(BookedLabAppointment.class);
                            mLabAppointmentList.add(appointment);
                        }
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
        mBinding.appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAppointmentAdapter = new AppointmentAdapter(mAppointmentList);
        mBinding.appointmentsRecyclerView.setAdapter(mAppointmentAdapter);

        mBinding.labAppointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLabAdapter = new BookedLabAdapter(mLabAppointmentList);
        mBinding.labAppointmentsRecyclerView.setAdapter(mLabAdapter);
    }
}