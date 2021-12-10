package com.example.proyek_mobcomp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyek_mobcomp.databinding.ActivityCustomerDetailProductBinding;
import com.example.proyek_mobcomp.databinding.ActivityCustomerHomeBinding;

public class CustomerDetailProduct extends AppCompatActivity {
    protected ActivityCustomerDetailProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail_product);

        binding = ActivityCustomerDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}