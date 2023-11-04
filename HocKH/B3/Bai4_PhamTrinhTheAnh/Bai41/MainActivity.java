package com.example.bai41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText numA, numB;
    TextView result;
    Button sumTwo, subTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        addEventFormWidgets();
    }
    private void getFormWidgets() {
        numA = findViewById(R.id.numA);
        numB = findViewById(R.id.numB);
        result = findViewById(R.id.result);
        sumTwo = findViewById(R.id.sumTwo);
        subTwo = findViewById(R.id.subTwo);
    }
    private void addEventFormWidgets() {
        sumTwo.setOnClickListener(new MyClickListener());
        subTwo.setOnClickListener(new MyClickListener());
    }
    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int a = 0;
            int b = 0;
            if(!numA.getText().toString().equals("")) {
                a = Integer.parseInt(numA.getText().toString());
            }
            if(!numB.getText().toString().equals("")) {
                b = Integer.parseInt(numB.getText().toString());
            }
            if(view == sumTwo) {
                result.setText(a + b + "");
            }
            if(view == subTwo) {
                result.setText(a - b + "");
            }
        }
    }
}
