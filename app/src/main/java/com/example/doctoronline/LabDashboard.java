package com.example.doctoronline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.doctoronline.adapter.LabTestAdapter;
import com.example.doctoronline.auth.LoginActivity;
import com.example.doctoronline.databinding.ActivityLabDashboardBinding;
import com.example.doctoronline.databinding.ActivityUserDashboardBinding;
import com.example.doctoronline.model.Lab;
import com.example.doctoronline.model.LabTest;

import java.util.ArrayList;
import java.util.List;

public class LabDashboard extends AppCompatActivity implements LabClickHandler  {
    ActivityLabDashboardBinding mBinding;
    private int mCounter = 0;
    private int totalBill = 0;
    public static boolean orderConfirmed = false;

    public static List<LabTest> mSelectedTests = new ArrayList<>();
    private List<LabTest> mLabTestList;
    private LabTestAdapter mLabTestAdapter;
    private String labName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLabDashboardBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mLabTestList = new ArrayList<>();

        Intent intent = getIntent();
        int imgId = intent.getIntExtra("image" , 0);
         labName= intent.getStringExtra("name");
        String time = intent.getStringExtra("time");
        String location = intent.getStringExtra("location");

        mBinding.name.setText(labName);
        mBinding.labImg.setImageResource(imgId);
        mBinding.time.setText(time);
        mBinding.labAddress.setText(location);
        mBinding.titleLab.setText(labName);

        mBinding.labTestsRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mLabTestList.add(new LabTest("Critinine Test" , 840 ,  1200));
        mLabTestList.add(new LabTest("Cholestrol Test" , 600 , 900));
        mLabTestList.add(new LabTest("Uric Acid Test" , 570 , 865));
        mLabTestList.add(new LabTest("Sugar Test" ,  50 , 120));
        mLabTestList.add(new LabTest("Blood Pressure Test" ,  0 , 50));

        mLabTestAdapter = new LabTestAdapter(mLabTestList , this);
        mBinding.labTestsRecyclerview.setAdapter(mLabTestAdapter);

        mBinding.addToCartBtn.setOnClickListener(v->{
            Intent intent1 = new Intent(this , CartActivity.class );
            intent1.putExtra("patient_name" , LoginActivity.presentUser.getUsername());
            intent1.putExtra("patient_age" , LoginActivity.presentUser.getAge());
            intent1.putExtra("patient_gender" , LoginActivity.presentUser.getGender());
            intent1.putExtra("bill" , totalBill);
            intent1.putExtra("name",labName);
            orderConfirmed = false;
            startActivity(intent1);
        });

        mBinding.backIcon.setOnClickListener(v->{
            finish();
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        if(orderConfirmed){
            totalBill = 0;
            mCounter = 0;
            mBinding.counter.setText(String.valueOf(mCounter));
            mSelectedTests.clear();
        }
    }

    @Override
    public void selectTest(Lab lab) {

    }

    @Override
    public void addToCart(LabTest labTest) {
        int amount = labTest.getPrice();
        totalBill = totalBill + amount;
        mCounter++;
        mBinding.counter.setText(String.valueOf(mCounter));
        mSelectedTests.add(labTest);

    }
}