package com.example.bai31_49_dothenhuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button sum, sub, mul, div;
    EditText inputA, inputB;
    TextView resul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sum = findViewById(R.id.sum);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);

        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        resul = findViewById(R.id.result);
        
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(inputA.getText() + "");
                int b = Integer.parseInt(inputB.getText() + "");

                resul.setText(a+"+"+b+"="+(a+b));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(inputA.getText() + "");
                int b = Integer.parseInt(inputB.getText() + "");

                resul.setText(a+"-"+b+"="+(a-b));

            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(inputA.getText() + "");
                int b = Integer.parseInt(inputB.getText() + "");

                resul.setText(a+"*"+b+"="+(a*b));
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(inputA.getText() + "");
                int b = Integer.parseInt(inputB.getText() + "");

                resul.setText(a+"/"+b+"="+(a/b));
            }
        });

    }
}