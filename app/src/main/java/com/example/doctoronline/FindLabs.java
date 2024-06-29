package com.example.doctoronline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.doctoronline.adapter.LabAdapter;
import com.example.doctoronline.databinding.ActivityFindLabsBinding;
import com.example.doctoronline.model.Lab;
import com.example.doctoronline.model.LabTest;

import java.util.ArrayList;
import java.util.List;

public class FindLabs extends AppCompatActivity implements LabClickHandler {
    private ActivityFindLabsBinding mBinding;

    private List<Lab> mLabList;
    private LabAdapter mLabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFindLabsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.backIcon.setOnClickListener(v->{
            finish();
        });

        mLabList = new ArrayList<>();

        mBinding.labsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLabList.add(new Lab(R.drawable.chugtai_lab , "Chugtai Lab" , "9:00 AM - 5:00 PM " , "Sargodha"));
        mLabList.add(new Lab(R.drawable.chugtai_lab , "Niazi Lab" , "9:00 AM - 5:00 PM " , "Sargodha"));
        mLabList.add(new Lab(R.drawable.chugtai_lab , "City Lab" , "9:00 AM - 5:00 PM " , "Sargodha"));
        mLabList.add(new Lab(R.drawable.chugtai_lab , "Sadiq Lab" , "9:00 AM - 5:00 PM " , "Sargodha"));


        mLabAdapter = new LabAdapter(mLabList , this);
        mBinding.labsRecyclerView.setAdapter(mLabAdapter);

    }

    @Override
    public void selectTest(Lab lab) {
        Intent intent = new Intent(this , LabDashboard.class);
        intent.putExtra("image" , lab.getImg());
        intent.putExtra("name" , lab.getName());
        intent.putExtra("time" , lab.getTime());
        intent.putExtra("location" , lab.getLocation());
        startActivity(intent);
    }

    @Override
    public void addToCart(LabTest labTest) {

    }
}