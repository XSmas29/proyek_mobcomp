package com.example.proyek_mobcomp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyek_mobcomp.databinding.FragmentCustomerHomeBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerHomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static CustomerHomeFragment newInstance(String param1, String param2) {
        CustomerHomeFragment fragment = new CustomerHomeFragment();
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

    protected FragmentCustomerHomeBinding binding;

    RecyclerAdapterCustomerHomeProduct recyclerAdapterCustomerHomeProduct;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_customer_home, container, false);
        binding = FragmentCustomerHomeBinding.inflate(inflater, container, false);

        binding.btnMoreCustHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v);
            }
        });
        loadcarousel();
        showProductByKategori();

        return binding.getRoot();
    }

    private void loadcarousel() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) + "/customer/loadcarousel",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println(jsonObject);
                            JSONArray arraycarousel = jsonObject.getJSONArray("foto");
                            if (arraycarousel != null) {
                                for (int i = 0; i < arraycarousel.length(); i++){
                                    int id = arraycarousel.getJSONObject(i).getInt("id");
                                    String fk_seller = arraycarousel.getJSONObject(i).getString("fk_seller");
                                    int fk_kategori = arraycarousel.getJSONObject(i).getInt("fk_kategori");
                                    String nama = arraycarousel.getJSONObject(i).getString("nama");
                                    String deskripsi = arraycarousel.getJSONObject(i).getString("deskripsi");
                                    int harga = arraycarousel.getJSONObject(i).getInt("harga");
                                    int stok = arraycarousel.getJSONObject(i).getInt("stok");
                                    String gambar = arraycarousel.getJSONObject(i).getString("gambar");

                                    CustomerHomeActivity.listCarousel.add(new cProduct(id, fk_seller, fk_kategori, nama, deskripsi, harga, stok, gambar));
                                }

                                ImageListener imageListener = new ImageListener() {
                                    @Override
                                    public void setImageForPosition(int position, ImageView imageView) {
                                        Picasso.get().load(getResources().getString(R.string.url) + "/produk/" +
                                                CustomerHomeActivity.listCarousel.get(position).getGambar()).into(imageView);
//                                        Picasso.get().load(getResources().getString(R.string.url) + "/produk/produk_3.jpg").into(imageView);
                                        System.out.println(getResources().getString(R.string.url) + "/produk/" +
                                                CustomerHomeActivity.listCarousel.get(position).getGambar());
                                    }
                                };

                                binding.carouselView.setImageListener(imageListener);
                                binding.carouselView.setPageCount(8);
                                binding.carouselView.setImageClickListener(new ImageClickListener() {
                                    @Override
                                    public void onClick(int position) {
                                        getProductDetail(CustomerHomeActivity.listCarousel.get(position).getId());
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error load carousel " + error);
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showProductByKategori() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) + "/showproductbykategori",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            //get data kategori
                            JSONArray arrayKategori = jsonObject.getJSONArray("datakategori");
                            CustomerHomeActivity.arrayListKategori.clear();
                            for (int i = 0; i < arrayKategori.length(); i++){
                                int id = arrayKategori.getJSONObject(i).getInt("id");
                                String nama = arrayKategori.getJSONObject(i).getString("nama");
                                String tipe = arrayKategori.getJSONObject(i).getString("tipe");

                                CustomerHomeActivity.arrayListKategori.add(
                                        new cKategori(id, nama, tipe)
                                );
                            }

                            // get data product yg akan ditampilkan
                            JSONArray arrayProduct = jsonObject.getJSONArray("dataproduct");
                            CustomerHomeActivity.arrayListProduct.clear();
                            //System.out.println("panjang array " + arrayProduct);
                            for (int i = 0; i < arrayProduct.length(); i++){
                                int id = arrayProduct.getJSONObject(i).getInt("id");
                                String fk_seller = arrayProduct.getJSONObject(i).getString("fk_seller");
                                int fk_kategori = arrayProduct.getJSONObject(i).getInt("fk_kategori");
                                String nama = arrayProduct.getJSONObject(i).getString("nama");
                                String deskripsi = arrayProduct.getJSONObject(i).getString("deskripsi");
                                int harga = arrayProduct.getJSONObject(i).getInt("harga");
                                int stok = arrayProduct.getJSONObject(i).getInt("stok");
                                String gambar = arrayProduct.getJSONObject(i).getString("gambar");

                                CustomerHomeActivity.arrayListProduct.add(
                                        new cProduct(id, fk_seller, fk_kategori, nama, deskripsi, harga, stok, gambar)
                                );
                            }



                            setRvProduct();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error saat ambil product by kategori " + error);
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("function","showproductbykategori");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void setRvProduct() {
        binding.recyclerViewCustHome.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewCustHome.setHasFixedSize(true);

        recyclerAdapterCustomerHomeProduct = new RecyclerAdapterCustomerHomeProduct(
                CustomerHomeActivity.arrayListKategori,
                CustomerHomeActivity.arrayListProduct
        );
        binding.recyclerViewCustHome.setAdapter(recyclerAdapterCustomerHomeProduct);
    }

    private void showPopUp(View v) {
        //buat popup menu nya dulu
        PopupMenu popupMenu = new PopupMenu(getContext(), v);
        getActivity().getMenuInflater().inflate(R.menu.optionsmenucustomer, popupMenu.getMenu());

        //event saat menu diklik
        //alt + enter kedua -> implements onMenuItemClick
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.wishlist){
                    Intent i = new Intent(getContext(), CustomerWishlistActivity.class);
                    startActivity(i);
                }else if(item.getItemId() == R.id.logout){
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

    protected void getProductDetail(int id){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) + "/customer/getdetailproduct",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject productObject = jsonObject.getJSONObject("datadetailproduct");
//                            for (int i = 0; i < arrayProduct.length(); i ++){
                            int id = productObject.getInt("id");
                            String fk_seller = productObject.getString("fk_seller");
                            int fk_kategori = productObject.getInt("fk_kategori");
                            String nama = productObject.getString("nama");
                            String deskripsi = productObject.getString("deskripsi");
                            int harga = productObject.getInt("harga");
                            int stok =productObject.getInt("stok");
                            String gambar = productObject.getString("gambar");

                            product = new cProduct(id, fk_seller, fk_kategori, nama, deskripsi, harga, stok, gambar);
//                            }
                            System.out.println("test");
                            JSONArray arrayRecProduct = jsonObject.getJSONArray("datarecommendproduct");
                            for (int i = 0; i < arrayRecProduct.length(); i ++){
                                id = arrayRecProduct.getJSONObject(i).getInt("id");
                                fk_seller = arrayRecProduct.getJSONObject(i).getString("fk_seller");
                                fk_kategori = arrayRecProduct.getJSONObject(i).getInt("fk_kategori");
                                nama = arrayRecProduct.getJSONObject(i).getString("nama");
                                deskripsi = arrayRecProduct.getJSONObject(i).getString("deskripsi");
                                harga = arrayRecProduct.getJSONObject(i).getInt("harga");
                                stok = arrayRecProduct.getJSONObject(i).getInt("stok");
                                gambar = arrayRecProduct.getJSONObject(i).getString("gambar");

                                arrRecommendationProduct.add(new cProduct(id, fk_seller, fk_kategori, nama, deskripsi, harga, stok, gambar));
                            }

                            showProduct();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error getdetailproduct = " + error.getMessage());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("function","getdetailproduct");
                params.put("idproduct", String.valueOf(id));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}