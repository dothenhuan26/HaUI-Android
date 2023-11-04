package com.example.bai42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnx;
    EditText inputYear;
    TextView outputYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnx = findViewById(R.id.btnx);
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputYear = findViewById(R.id.inputYear);
                String inputText = inputYear.getText().toString();

                try {
                    int x = Integer.parseInt(inputText);

                    int can = x % 10, chi = x % 12;
                    String tinhCan = "", tinhChi = "";
                    switch (can) {
                        case 0:
                            tinhCan = "Canh";
                            break;
                        case 1:
                            tinhCan = "Tân";
                            break;
                        case 2:
                            tinhCan = "Nhâm";
                            break;
                        case 3:
                            tinhCan = "Quý";
                            break;
                        case 4:
                            tinhCan = "Giáp";
                            break;
                        case 5:
                            tinhCan = "Ất";
                            break;
                        case 6:
                            tinhCan = "Bính";
                            break;
                        case 7:
                            tinhCan = "Đinh";
                            break;
                        case 8:
                            tinhCan = "Mậu";
                            break;
                        case 9:
                            tinhCan = "Kỷ";
                            break;
                    }
                    switch (chi) {
                        case 0:
                            tinhChi = "Thân";
                            break;
                        case 1:
                            tinhChi = "Dậu";
                            break;
                        case 2:
                            tinhChi = "Tuất";
                            break;
                        case 3:
                            tinhChi = "Hợi";
                            break;
                        case 4:
                            tinhChi = "Tý";
                            break;
                        case 5:
                            tinhChi = "Sửu";
                            break;
                        case 6:
                            tinhChi = "Dần";
                            break;
                        case 7:
                            tinhChi = "Mão";
                            break;
                        case 8:
                            tinhChi = "Thìn";
                            break;
                        case 9:
                            tinhChi = "Tỵ";
                            break;
                        case 10:
                            tinhChi = "Ngọ";
                            break;
                        case 11:
                            tinhChi = "Mùi";
                            break;
                    }

                    outputYear = findViewById(R.id.outputYear);
                    outputYear.setText(tinhCan + " "+ tinhChi + "");
                } catch (NumberFormatException e) {
                    inputYear.setText("");
                    inputYear.requestFocus();
                }
            }
        });
    }
}
