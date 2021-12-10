package com.example.proyek_mobcomp.recyclerviewFolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyek_mobcomp.CustomerDetailProduct;
import com.example.proyek_mobcomp.CustomerHomeActivity;
import com.example.proyek_mobcomp.R;
import com.example.proyek_mobcomp.classFolder.cProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterCustomerDetailProduct extends RecyclerView.Adapter<RecyclerAdapterCustomerDetailProduct.ViewHolder> {
    protected ArrayList<cProduct> arrProduct = new ArrayList<>();
    protected int ctrBarang = 0;
    public RecyclerAdapterCustomerDetailProduct(ArrayList<cProduct> arrProduct) {
        this.arrProduct = arrProduct;
        //System.out.println(this.arrProduct.size());
    }

    @NonNull
    @Override
    public RecyclerAdapterCustomerDetailProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_layout_barang_by_kategori, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCustomerDetailProduct.ViewHolder holder, int position) {
        cProduct product = arrProduct.get(position);
        holder.bind(product, position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llKategoriContainer, llProductContainer;
        TextView txtNamaKategori;
        LinearLayout[] arrLlProduct = new LinearLayout[5];
        ImageView[] arrImageView = new ImageView[5];
        TextView[] arrTxtNamaProduct = new TextView[5];
        TextView[] arrTxtHargaProduct = new TextView[5];
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llKategoriContainer = itemView.findViewById(R.id.llKategoriContainer);
            llProductContainer = itemView.findViewById(R.id.llProductContainer);
            txtNamaKategori = itemView.findViewById(R.id.textView_namaKategori);

            arrLlProduct[0] = itemView.findViewById(R.id.ll0);
            arrLlProduct[1] = itemView.findViewById(R.id.ll1);
            arrLlProduct[2] = itemView.findViewById(R.id.ll2);
            arrLlProduct[3] = itemView.findViewById(R.id.ll3);
            arrLlProduct[4] = itemView.findViewById(R.id.ll4);

            arrImageView[0] = itemView.findViewById(R.id.imageView_product0);
            arrImageView[1] = itemView.findViewById(R.id.imageView_product1);
            arrImageView[2] = itemView.findViewById(R.id.imageView_product2);
            arrImageView[3] = itemView.findViewById(R.id.imageView_product3);
            arrImageView[4] = itemView.findViewById(R.id.imageView_product4);

            arrTxtNamaProduct[0] = itemView.findViewById(R.id.textView_namaProduct0);
            arrTxtNamaProduct[1] = itemView.findViewById(R.id.textView_namaProduct1);
            arrTxtNamaProduct[2] = itemView.findViewById(R.id.textView_namaProduct2);
            arrTxtNamaProduct[3] = itemView.findViewById(R.id.textView_namaProduct3);
            arrTxtNamaProduct[4] = itemView.findViewById(R.id.textView_namaProduct4);

            arrTxtHargaProduct[0] = itemView.findViewById(R.id.textView_hargaProduct0);
            arrTxtHargaProduct[1] = itemView.findViewById(R.id.textView_hargaProduct1);
            arrTxtHargaProduct[2] = itemView.findViewById(R.id.textView_hargaProduct2);
            arrTxtHargaProduct[3] = itemView.findViewById(R.id.textView_hargaProduct3);
            arrTxtHargaProduct[4] = itemView.findViewById(R.id.textView_hargaProduct4);
        }

        public void bind(cProduct product, int position) {
//            if (ctrBarang < 5) {
//                Picasso.get().load(itemView.getResources().getString(R.string.url) + "/produk/" +
//                        product.getGambar()).into(arrImageView[ctrBarang]);
//
//                ViewGroup.LayoutParams params = arrImageView[ctrBarang].getLayoutParams();
//                params.height = 120;
//                arrImageView[ctrBarang].setLayoutParams(params);
//
//                arrTxtNamaProduct[ctrBarang].setText(product.getNama());
//                arrTxtHargaProduct[ctrBarang].setText("Rp " + product.getHarga());
//
//                int idProduct = product.getId();
//                arrLlProduct[0].setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent i = new Intent(itemView.getContext(), CustomerDetailProduct.class);
//                        i.putExtra("idproduct", idProduct);
//                        i.putExtra("login", CustomerHomeActivity.login);
//                        ((Activity) itemView.getContext()).startActivityForResult(i, 100);
//                    }
//                });
//
//                ctrBarang++;
//            }


        }
    }
}
