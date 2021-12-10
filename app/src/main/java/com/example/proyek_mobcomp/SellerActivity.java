package com.example.proyek_mobcomp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyek_mobcomp.databinding.ActivityMainBinding;
import com.example.proyek_mobcomp.databinding.ActivitySellerBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SellerActivity extends AppCompatActivity {

    protected ActivitySellerBinding binding;

    String login;

    public void showFragment(int idx) {
        Bundle bundle = new Bundle();
        //bundle.putString("login", login);
        if (idx == 0){
            SellerDashboardFragment sellerDashboardFragment = new SellerDashboardFragment();
            sellerDashboardFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frContainer, sellerDashboardFragment);
            fragmentTransaction.commit();
        }
//        else if (idx == 1){
//            Seller customerSearchFragment = new CustomerSearchFragment();
//            customerSearchFragment.setArguments(bundle);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frContainer, customerSearchFragment);
//            fragmentTransaction.commit();
//        }else if (idx == 2){
//            CustomerCartFragment customerCartFragment = new CustomerCartFragment();
//            customerCartFragment.setArguments(bundle);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frContainer, customerCartFragment);
//            fragmentTransaction.commit();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        binding = ActivitySellerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        login = getIntent().getStringExtra("login");

        showFragment(0);

        binding.bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.sellerdashboard) {
                    showFragment(0);
                }else if(item.getItemId() == R.id.selleraddbarang) {
                    showFragment(1);
                }
                else if(item.getItemId() == R.id.sellersaldo) {
                    showFragment(2);
                }
                else if(item.getItemId() == R.id.sellerhistory) {
                    showFragment(3);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenuseller, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}