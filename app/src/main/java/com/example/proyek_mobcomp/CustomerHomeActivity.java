package com.example.proyek_mobcomp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.proyek_mobcomp.databinding.ActivityCustomerHomeBinding;
import com.example.proyek_mobcomp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CustomerHomeActivity extends AppCompatActivity {
    protected ActivityCustomerHomeBinding binding;

    public static ArrayList<cKategori> arrayListKategori = new ArrayList<>();
    public static ArrayList<cProduct> arrayListProduct = new ArrayList<>();

    String login;

    public void showFragment(int idx) {
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        if (idx == 0){
            CustomerHomeFragment customerHomeFragment = new CustomerHomeFragment();
            customerHomeFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frContainer, customerHomeFragment);
            fragmentTransaction.commit();
        }else if (idx == 1){
            CustomerSearchFragment customerSearchFragment = new CustomerSearchFragment();
            customerSearchFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frContainer, customerSearchFragment);
            fragmentTransaction.commit();
        }else if (idx == 2){
            CustomerCartFragment customerCartFragment = new CustomerCartFragment();
            customerCartFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frContainer, customerCartFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        binding = ActivityCustomerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        login = getIntent().getStringExtra("login");

        showFragment(0);

        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.customerhome) {
                    showFragment(0);
                }else if(item.getItemId() == R.id.customersearch) {
                    showFragment(1);
                }
                else if(item.getItemId() == R.id.customercart) {
                    showFragment(2);
                }
                return true;
            }
        });
    }
}