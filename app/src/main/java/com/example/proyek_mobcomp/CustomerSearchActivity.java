package com.example.proyek_mobcomp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyek_mobcomp.classFolder.cProduct;
import com.example.proyek_mobcomp.classFolder.cUser;
import com.example.proyek_mobcomp.databinding.ActivityCustomerHomeBinding;
import com.example.proyek_mobcomp.databinding.ActivityCustomerSearchBinding;
import com.example.proyek_mobcomp.recyclerviewFolder.RecyclerAdapterCustomerHomeProduct;
import com.example.proyek_mobcomp.recyclerviewFolder.RecyclerAdapterCustomerSearchProduct;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerSearchActivity extends AppCompatActivity {

    ActivityCustomerSearchBinding binding;
    ArrayList<cProduct> listSearch = new ArrayList<>();
    ArrayList<String[]> listowner = new ArrayList<>();
    RecyclerAdapterCustomerSearchProduct recyclerAdapterCustomerSearchProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);

        binding = ActivityCustomerSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent().hasExtra("keyword")){
            binding.txtKeyword.setText("Search Result for '" + getIntent().getStringExtra("keyword") + "'");
            loadSearch();
        }
    }

    private void loadSearch() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) + "/customer/search",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println(jsonObject);
                            JSONArray arraysearch = jsonObject.getJSONArray("databarang");

                            for (int i = 0; i < arraysearch.length(); i++){
                                int id = arraysearch.getJSONObject(i).getInt("id");
                                String fk_seller = arraysearch.getJSONObject(i).getString("fk_seller");
                                int fk_kategori = arraysearch.getJSONObject(i).getInt("fk_kategori");
                                String nama = arraysearch.getJSONObject(i).getString("nama");
                                String deskripsi = arraysearch.getJSONObject(i).getString("deskripsi");
                                int harga = arraysearch.getJSONObject(i).getInt("harga");
                                int stok = arraysearch.getJSONObject(i).getInt("stok");
                                String gambar = arraysearch.getJSONObject(i).getString("gambar");
                                int is_deleted = arraysearch.getJSONObject(i).getInt("is_deleted");

                                //data toko
                                JSONObject dataowner = arraysearch.getJSONObject(i).getJSONObject("owner");
                                String namatoko = dataowner.getString("toko");
                                String gambartoko = dataowner.getString("gambar");

                                listSearch.add(
                                        new cProduct(id, fk_seller, fk_kategori, nama, deskripsi, harga, stok, gambar, is_deleted)
                                );

                                listowner.add(new String[] {namatoko, gambartoko});
                            }
                            setRvSearchGrid();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error search : " + error.getMessage());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("keyword", String.valueOf(getIntent().getStringExtra("keyword")));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setRvSearchGrid() {
        binding.rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvSearch.setHasFixedSize(true);

        recyclerAdapterCustomerSearchProduct = new RecyclerAdapterCustomerSearchProduct(listSearch, listowner);
        binding.rvSearch.setAdapter(recyclerAdapterCustomerSearchProduct);

    }
}