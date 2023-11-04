package com.example.bai3_49_dothenhuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button sum, sub;
    EditText inputA, inputB;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("are u ok?");
        dialog.setTitle("Are U sure");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });


        CheckBox chk = (CheckBox)findViewById(R.id.chk);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    dialog.create().show();
                }
            }
        });

        RadioGroup radios = (RadioGroup) findViewById(R.id.radios);




        sum = findViewById(R.id.sum);
        sub = findViewById(R.id.sub);
        result = findViewById(R.id.result);
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(inputA.getText() + "");
                int b = Integer.parseInt(inputB.getText() + "");
                result.setText((a + b) + "");
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(inputA.getText() + "");
                int b = Integer.parseInt(inputB.getText() + "");
                result.setText((a - b) + "");
            }
        });

    }
}