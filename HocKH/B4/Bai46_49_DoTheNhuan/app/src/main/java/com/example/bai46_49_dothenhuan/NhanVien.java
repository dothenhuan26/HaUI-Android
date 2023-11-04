package com.example.bai46_49_dothenhuan;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

public class NhanVien {
    private String id;
    private String name;
    private boolean type;

    public NhanVien(String id, String name, boolean type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return ""+((isType())?"FullTime:":"PartTime:")+getId()+" - "+getName()+"-"+((isType())?"500.00":"150.00");
    }
}
