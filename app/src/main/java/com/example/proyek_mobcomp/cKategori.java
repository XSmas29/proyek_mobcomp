package com.example.proyek_mobcomp;

import android.os.Parcel;
import android.os.Parcelable;

public class cKategori implements Parcelable {
    protected int id;
    protected String nama, tipe;

    public cKategori(int id, String nama, String tipe) {
        this.id = id;
        this.nama = nama;
        this.tipe = tipe;
    }

    protected cKategori(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        tipe = in.readString();
    }

    public static final Creator<cKategori> CREATOR = new Creator<cKategori>() {
        @Override
        public cKategori createFromParcel(Parcel in) {
            return new cKategori(in);
        }

        @Override
        public cKategori[] newArray(int size) {
            return new cKategori[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(tipe);
    }
}
