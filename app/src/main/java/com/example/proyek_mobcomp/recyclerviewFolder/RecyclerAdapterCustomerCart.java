package com.example.proyek_mobcomp.recyclerviewFolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek_mobcomp.R;
import com.example.proyek_mobcomp.classFolder.cCart;
import com.example.proyek_mobcomp.classFolder.cKategori;

import java.util.ArrayList;

public class RecyclerAdapterCustomerCart extends RecyclerView.Adapter<RecyclerAdapterCustomerCart.ViewHolder> {
    ArrayList<cCart> arrCart = new ArrayList<>();


    public RecyclerAdapterCustomerCart(ArrayList<cCart> arrCart) {
        this.arrCart = arrCart;
    }

    @NonNull
    @Override
    public RecyclerAdapterCustomerCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_layout_cart, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCustomerCart.ViewHolder holder, int position) {
        cCart cart = arrCart.get(position);
        holder.bind(cart, position);
    }

    @Override
    public int getItemCount() {
        return arrCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileToko, fotoProduct;
        TextView txtNamaToko, txtNamaProduct, txtHargaProduct, txtJumlah, txtTotal;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileToko = itemView.findViewById(R.id.imageView_profileToko);
            fotoProduct = itemView.findViewById(R.id.imageView_productPicture);
            txtNamaToko = itemView.findViewById(R.id.imageView_profileToko);
            txtNamaProduct = itemView.findViewById(R.id.textView_namaToko);
            txtHargaProduct = itemView.findViewById(R.id.textView_productPrice);
            txtJumlah = itemView.findViewById(R.id.textView_productJumlah);
            txtTotal = itemView.findViewById(R.id.textView_totalPrice);
            btnDelete = itemView.findViewById(R.id.imageButton_delete);
        }

        public void bind(cCart cart, int position) {
        }
    }
}
