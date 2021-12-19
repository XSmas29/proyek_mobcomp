package com.example.proyek_mobcomp.classFolder;

public class cDetailPurchase {
    protected int id;
    protected int fk_htrans;
    protected int fk_barang;
    protected int jumlah;
    protected int subtotal;
    protected int rating;
    protected String review;
    protected String fk_seller;
    protected String status;
    protected String notes_seller;
    protected String notes_customer;

    public cDetailPurchase(int id, int fk_htrans, int fk_barang
            , int jumlah, int subtotal, int rating
            , String review, String fk_seller, String status
            , String notes_seller, String notes_customer) {
        this.id = id;
        this.fk_htrans = fk_htrans;
        this.fk_barang = fk_barang;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
        this.rating = rating;
        this.review = review;
        this.fk_seller = fk_seller;
        this.status = status;
        this.notes_seller = notes_seller;
        this.notes_customer = notes_customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_htrans() {
        return fk_htrans;
    }

    public void setFk_htrans(int fk_htrans) {
        this.fk_htrans = fk_htrans;
    }

    public int getFk_barang() {
        return fk_barang;
    }

    public void setFk_barang(int fk_barang) {
        this.fk_barang = fk_barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getFk_seller() {
        return fk_seller;
    }

    public void setFk_seller(String fk_seller) {
        this.fk_seller = fk_seller;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes_seller() {
        return notes_seller;
    }

    public void setNotes_seller(String notes_seller) {
        this.notes_seller = notes_seller;
    }

    public String getNotes_customer() {
        return notes_customer;
    }

    public void setNotes_customer(String notes_customer) {
        this.notes_customer = notes_customer;
    }
}