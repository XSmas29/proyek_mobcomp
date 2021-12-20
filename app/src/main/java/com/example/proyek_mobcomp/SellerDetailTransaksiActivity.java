package com.example.proyek_mobcomp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyek_mobcomp.classFolder.cDetailPurchase;
import com.example.proyek_mobcomp.classFolder.cProduct;
import com.example.proyek_mobcomp.databinding.ActivitySellerDetailTransaksiBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SellerDetailTransaksiActivity extends AppCompatActivity {

    ActivitySellerDetailTransaksiBinding binding;

    cProduct produk;
    cDetailPurchase detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_detail_transaksi);

        binding = ActivitySellerDetailTransaksiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        produk = getIntent().getParcelableExtra("produk");
        detail = getIntent().getParcelableExtra("detail");

        //set text
        binding.lbSellerDetailIDTransaksi.setText("Transaksi #" + detail.getId());

        Picasso.get().load(getResources().getString(R.string.url) + "/produk/" +
                produk.getGambar()).into(binding.imgSellerGambarDetail);

        binding.lbSellerDetailNamaTransaksi.setText("Nama Barang : " + produk.getNama());
        binding.lbSellerDetailJumlahTransaksi.setText("Jumlah : " + detail.getJumlah());
        binding.edNotesCustomer.setText(detail.getNotes_customer());


        if (detail.getStatus().equalsIgnoreCase("pending")){
            binding.layoutSellerDetail1.setVisibility(View.VISIBLE);
            binding.lbSellerStatusDetail.setTextColor(getResources().getColor(R.color.yellow));
            binding.lbSellerStatusDetail.setText("Status : Pending");
        }
        else if (detail.getStatus().equalsIgnoreCase("processing")){
            binding.layoutSellerDetail2.setVisibility(View.VISIBLE);
            binding.lbSellerStatusDetail.setTextColor(getResources().getColor(R.color.yellow));
            binding.lbSellerStatusDetail.setText("Status : Processing");
        }
        else if (detail.getStatus().equalsIgnoreCase("sent")){
            binding.lbSellerStatusDetail.setTextColor(getResources().getColor(R.color.yellow));
            binding.lbSellerStatusDetail.setText("Status : Sent");
        }
        else if (detail.getStatus().equalsIgnoreCase("completed")){
            binding.lbSellerStatusDetail.setTextColor(getResources().getColor(R.color.green));
            binding.lbSellerStatusDetail.setText("Status : Completed");
        }
        else{
            binding.lbSellerStatusDetail.setTextColor(getResources().getColor(R.color.red));
            binding.lbSellerStatusDetail.setText("Status : Rejected");
        }

        binding.btnSellerRejectTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTransaksi("rejected");
            }
        });

        binding.btnSellerAcceptTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTransaksi("processing");
            }
        });

        binding.btnSellerSendTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edNotesSeller.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Notes Seller Harus diisi!", Toast.LENGTH_SHORT).show();
                }
                else{
                    updateTransaksi("sent");
                }

            }
        });
    }

    private void updateTransaksi(String status) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) + "/seller/updatetransaksi",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            int code = jsonObject.getInt("code");
                            if (code == 1){
                                Toast.makeText(getApplicationContext(), "Berhasil Reject Transaksi!", Toast.LENGTH_SHORT).show();
                                detail.setStatus("rejected");
                                Intent i = new Intent(getApplicationContext(), SellerDetailTransaksiActivity.class);
                                i.putExtra("produk", produk);
                                i.putExtra("detail", detail);
                                startActivity(i);
                            }
                            else if (code == 2){
                                Toast.makeText(getApplicationContext(), "Berhasil Accept Transaksi!", Toast.LENGTH_SHORT).show();
                                detail.setStatus("processing");
                                Intent i = new Intent(getApplicationContext(), SellerDetailTransaksiActivity.class);
                                i.putExtra("produk", produk);
                                i.putExtra("detail", detail);
                                startActivity(i);
                            }
                            else if (code == 3){
                                Toast.makeText(getApplicationContext(), "Berhasil Send Produk!", Toast.LENGTH_SHORT).show();
                                detail.setStatus("sent");
                                Intent i = new Intent(getApplicationContext(), SellerDetailTransaksiActivity.class);
                                i.putExtra("produk", produk);
                                i.putExtra("detail", detail);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Stok Barang Tidak Cukup!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error update transaksi : " + error.getMessage());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("status", status);
                params.put("id", detail.getId() + "");
                params.put("noteseller", binding.edNotesSeller.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, SellerActivity.class);
        i.putExtra("login", SellerActivity.login);
        startActivity(i);
    }
}