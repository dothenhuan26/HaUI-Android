package com.example.a49_dothenhuan_2021600381;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class LuyenTap implements Parcelable {

    private String ngay;
    private int soKm;
    private String buoi;

    public LuyenTap() {
    }

    public LuyenTap(String ngay, int soKm, String buoi) {
        this.ngay = ngay;
        this.soKm = soKm;
        this.buoi = buoi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSoKm() {
        return soKm;
    }

    public void setSoKm(int soKm) {
        this.soKm = soKm;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    @Override
    public String toString() {
        return this.ngay + "-" + this.soKm + "-" + this.buoi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}
