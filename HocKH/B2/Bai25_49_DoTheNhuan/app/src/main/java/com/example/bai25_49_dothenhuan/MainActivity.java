package com.example.bai25_49_dothenhuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity Lifecycle";
    Button btnSub1, btnSub2, btnCls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSub1 = findViewById(R.id.screen_1);
        btnSub2 = findViewById(R.id.screen_2);
        btnCls = findViewById(R.id.exit);
        btnCls.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSub1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, SubActivity1.class);
                startActivity(intent1);
            }
        });

        btnSub2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, SubActivity2.class);
                startActivity(intent2);
            }
        });

    }

    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Đang gọi tới MainActivity.onRestart", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "===== onRestart =====");
    }

    protected void onStart() {

        super.onStart();
        Toast.makeText(this, "Đang gọi tới MainActivity.onStart", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "===== onStart =====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Đang gọi tới MainActivity.onResum", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "===== onResum =====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Đang gọi tới MainActivity.onPause", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "===== onPause =====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Đang gọi tới MainActivity.onStop", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "===== onStop =====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Đang gọi tới MainActivity.onDestroy", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "===== onDestroy =====");
    }
}