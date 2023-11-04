package com.example.bai45_49_dothenhuan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {
    Activity context;
    int layoutID;
    ArrayList<String> list=null;
    public MyAdapter(@NonNull Activity context, int resource, @NonNull
    List objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.list = (ArrayList<String>) objects;
    }
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if ((list.size() > 0) && (position >= 0)) {
            //laays dongf thuws i
            final TextView txtSTT =
                    convertView.findViewById(R.id.txtStt);
            final TextView txtNoiDung =
                    convertView.findViewById(R.id.txtNoiDung);
            //laays ban ghi thu possition gans cho thanh phan tuong ung
            txtSTT.setText(position + "");
            txtNoiDung.setText(list.get(position).toString() + "");
        }
        return convertView;
    }

}
