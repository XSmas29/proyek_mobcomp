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
import com.example.proyek_mobcomp.cKategori;
import com.example.proyek_mobcomp.classFolder.cProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterCustomerSearchProduct extends RecyclerView.Adapter<RecyclerAdapterCustomerSearchProduct.ViewHolder>{

    ArrayList<cProduct> arrProduct = new ArrayList<>();
    ArrayList<String[]> arrOwner = new ArrayList<>();

    public RecyclerAdapterCustomerSearchProduct(ArrayList<cProduct> arrProduct, ArrayList<String[]> arrOwner) {
        this.arrProduct = arrProduct;
        this.arrOwner = arrOwner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_search_grid, parent, false);

        RecyclerAdapterCustomerSearchProduct.ViewHolder viewHolder = new RecyclerAdapterCustomerSearchProduct.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        cProduct product = arrProduct.get(position);
        holder.bind(product, position);
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView namaProduct;
        TextView hargaProduct;

        ImageView imgToko;
        TextView namaToko;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.imgSearchProduk);
            namaProduct = itemView.findViewById(R.id.lbNamaSearchProduk);
            hargaProduct = itemView.findViewById(R.id.lbHargaSearchProduk);

            imgToko = itemView.findViewById(R.id.imgSearchToko);
            namaToko = itemView.findViewById(R.id.lbSearchToko);
        }

        public void bind(cProduct product, int position) {
            Picasso.get().load(itemView.getResources().getString(R.string.url) + "/produk/" +
                    arrProduct.get(position).getGambar()).into(imgProduct);

            namaProduct.setText(product.getNama());
            hargaProduct.setText("Rp. " + product.getHarga());


            Picasso.get().load(itemView.getResources().getString(R.string.url) + "/profile/" +
                    arrOwner.get(position)[1]).into(imgToko);

            System.out.println(itemView.getResources().getString(R.string.url) + "/profile/" +
                    arrOwner.get(position)[1]);

            namaToko.setText(arrOwner.get(position)[0]);
        }
    }
}
