package com.example.proyek_mobcomp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.proyek_mobcomp.databinding.ActivityCustomerHomeBinding;
import com.example.proyek_mobcomp.databinding.ActivityMainBinding;

public class CustomerHomeActivity extends AppCompatActivity {
    protected ActivityCustomerHomeBinding binding;

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
//            EditProfileFragment editProfileFragment = new EditProfileFragment();
//            editProfileFragment.setArguments(bundle);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frContainer, editProfileFragment);
//            fragmentTransaction.commit();
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
    }
}