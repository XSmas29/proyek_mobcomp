package com.example.proyek_mobcomp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyek_mobcomp.databinding.FragmentSellerAddBarangBinding;

public class SellerListBarangFragment extends Fragment {

    FragmentSellerAddBarangBinding binding;

    public static SellerListBarangFragment newInstance() {
        SellerListBarangFragment fragment = new SellerListBarangFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSellerAddBarangBinding.inflate(getLayoutInflater());



        return binding.getRoot();
    }
}