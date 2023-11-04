package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] cities = {"Ha Noi", "Hai Phong", "Da Nang", "Ho Chi Minh", "Can Tho"};
    TextView txtCity;
    ListView lvCity;
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        addEventFormWidgets();
    }
    public void getFormWidgets() {
        txtCity = findViewById(R.id.txtOut);
        lvCity = findViewById(R.id.lvCity);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        lvCity.setAdapter(adapter);
    }
    public void addEventFormWidgets() {
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String output = "";
                output = "Index: " + (i + 1) + "; Value = " + cities[i];
                txtCity.setText(output);
            }
        });
    }

}