package com.example.proyek_mobcomp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyek_mobcomp.databinding.ActivityAdminBinding;
import com.example.proyek_mobcomp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {

    protected ActivityAdminBinding binding;

    String login;

    public void showFragment(int idx) {
        Bundle bundle = new Bundle();
        bundle.putString("login", login);
        if (idx == 0){
            AdminDashboardFragment adminDashboardFragment = new AdminDashboardFragment();
            adminDashboardFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frContainer, adminDashboardFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        login = getIntent().getStringExtra("login");

        showFragment(0);

        binding.navBottom.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bottomdashboard) {
                    showFragment(0);
                }
                else if(item.getItemId() == R.id.bottommasteruser) {
                    showFragment(1);
                }
                else if(item.getItemId() == R.id.bottomconftopup) {
                    showFragment(2);
                }
                else if(item.getItemId() == R.id.bottomconfwithdraw) {
                    showFragment(3);
                }
                return true;
            }
        });
    }
}