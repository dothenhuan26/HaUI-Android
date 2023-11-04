package com.example.b25_49_dothenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnCls;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        btnCls = findViewById(R.id.btnTroVe1);
        btnCls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}