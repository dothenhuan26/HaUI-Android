package com.example.a49_dothenhuan_2021600381;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    ListView lv;
    Button back, send;

    ArrayAdapter<String> adapter;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        getWidget();

        back.setOnClickListener(new DoSomething());
        send.setOnClickListener(new DoSomething());
    }

    class DoSomething implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.back) {
                Intent main = new Intent(Activity2.this, MainActivity.class);
                startActivity(main);
            }

            if (v.getId() == R.id.send) {
                String phoneNumber = "19001900";
                String message = "Do The Nhuan";
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "SMS failed to send.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }
    }

    public void getWidget() {
        lv = findViewById(R.id.lv);
        back = findViewById(R.id.back);
        send = findViewById(R.id.send);

        Intent intent = this.getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            list = intent.getStringArrayListExtra("list");
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

    }
}