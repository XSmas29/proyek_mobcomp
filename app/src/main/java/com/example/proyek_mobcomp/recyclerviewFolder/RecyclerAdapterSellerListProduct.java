package com.example.proyek_mobcomp.recyclerviewFolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek_mobcomp.R;
import com.example.proyek_mobcomp.classFolder.cProduct;
import com.example.proyek_mobcomp.classFolder.cReview;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterSellerListProduct extends RecyclerView.Adapter<RecyclerAdapterSellerListProduct.ViewHolder> {

    ArrayList<cProduct> listProduk = new ArrayList<>();

    public RecyclerAdapterSellerListProduct(ArrayList<cProduct> listProduk) {
        this.listProduk = listProduk;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_barang_seller, parent, false);

        RecyclerAdapterSellerListProduct.ViewHolder viewHolder = new RecyclerAdapterSellerListProduct.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cProduct produk = listProduk.get(position);
        holder.bind(produk, position);
    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;

        TextView txtNamaProduk;
        TextView txtHargaProduk;
        TextView txtStokProduk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduk = itemView.findViewById(R.id.imgListProduk);
            txtNamaProduk = itemView.findViewById(R.id.lbNamaListProduk);
            txtHargaProduk = itemView.findViewById(R.id.lbHargaListProduk);
            txtStokProduk = itemView.findViewById(R.id.lbStokListProduk);
        }

        public void bind(cProduct produk, int position) {
            Picasso.get()
                    .load(itemView.getResources().getString(R.string.url) + "/produk/" + listProduk.get(position).getGambar())
                    .into(imgProduk);

            txtNamaProduk.setText(listProduk.get(position).getNama());
            txtHargaProduk.setText("Rp. " + listProduk.get(position).getHarga());
            txtStokProduk.setText("Stok : " + listProduk.get(position).getStok());
        }
    }
}
