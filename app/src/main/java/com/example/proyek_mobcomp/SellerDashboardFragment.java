package com.example.proyek_mobcomp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.example.proyek_mobcomp.databinding.FragmentCustomerHomeBinding;
import com.example.proyek_mobcomp.databinding.FragmentSellerDashboardBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerDashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerDashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellerDashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SellerDashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SellerDashboardFragment newInstance(String param1, String param2) {
        SellerDashboardFragment fragment = new SellerDashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    protected FragmentSellerDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_seller_dashboard, container, false);
        binding = FragmentSellerDashboardBinding.inflate(inflater, container, false);

        binding.btnMoreSellerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v);
            }
        });

        return binding.getRoot();
    }


    private void showPopUp(View v) {
        //buat popup menu nya dulu
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        getActivity().getMenuInflater().inflate(R.menu.optionsmenuseller, popupMenu.getMenu());

        //event saat menu diklik
        //alt + enter kedua -> implements onMenuItemClick
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.logout){
                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences("data", getActivity().MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.remove("login");
                    editor.commit();
                    getActivity().finish();
                }
                return true;
            }
        });

        //munculkan popupmenu
        popupMenu.show();
    }
}