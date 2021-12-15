package com.example.proyek_mobcomp.classFolder;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart")
public class cCart {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    protected int idCart;

    protected int idProduct;
    protected String username;
    protected int jumlah;

    public cCart(int idProduct, String username, int jumlah) {
        this.idProduct = idProduct;
        this.username = username;
        this.jumlah = jumlah;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

}
