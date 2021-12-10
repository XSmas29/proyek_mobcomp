package com.example.proyek_mobcomp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyek_mobcomp.classFolder.cProduct;
import com.example.proyek_mobcomp.databinding.ActivityCustomerDetailProductBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerDetailProduct extends AppCompatActivity {
    protected ActivityCustomerDetailProductBinding binding;
    int idProduct = -1;

    cProduct product;
    ArrayList<cProduct> arrRecommendationProduct = new ArrayList<>();

    int isWishlisted  = 0; // 0 = not wishlist, 1 = wishlisted
    int idWishlist = -1; // id wishlist, jika diwishlist

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail_product);

        binding = ActivityCustomerDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idProduct = getIntent().getIntExtra("idproduct", -1);

        if (idProduct != -1) {
            getProductDetail();
        }


        // btn wishilist
        binding.imageButtonWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageButtonWishlist.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                isWishlisted = 0;
                idWishlist = -1;
            }
        });
    }

    protected void getProductDetail(){
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
                                int is_deleted = productObject.getInt("is_deleted");

                                product = new cProduct(id, fk_seller, fk_kategori, nama, deskripsi, harga, stok, gambar, is_deleted);
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
                                is_deleted = arrayRecProduct.getJSONObject(i).getInt("is_deleted");

                                arrRecommendationProduct.add(new cProduct(id, fk_seller, fk_kategori, nama, deskripsi, harga, stok, gambar, is_deleted));
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
                params.put("idproduct", String.valueOf(idProduct));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    protected void showProduct() {
        Picasso.get().load(getResources().getString(R.string.url) + "/produk/" +
                product.getGambar()).into(binding.imageViewProductPicture);

        binding.textViewProductName.setText(product.getNama());
        binding.textViewProductPrice.setText("Rp " + product.getHarga());
        binding.textViewProductDescription.setText("Detail produk : \n"+product.getDeskripsi());

        for (int i = 0; i < CustomerHomeActivity.arrayListWishlist.size(); i++){
            if (CustomerHomeActivity.arrayListWishlist.get(i).getFk_barang() == product.getId()
                && CustomerHomeActivity.arrayListWishlist.get(i).getFk_user().equals(CustomerHomeActivity.login)){
                binding.imageButtonWishlist.setImageResource(R.drawable.ic_baseline_favorite_24);
                isWishlisted = 1;
                idWishlist = CustomerHomeActivity.arrayListWishlist.get(i).getId();
            }
        }
    }
}