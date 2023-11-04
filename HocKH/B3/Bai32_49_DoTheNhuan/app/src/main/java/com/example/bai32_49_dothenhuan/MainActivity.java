package com.example.bai32_49_dothenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button handle, clear, finish;
    EditText inputA, inputB, inputC;

    TextView result;
    int a, b, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        inputC = findViewById(R.id.inputC);
        handle = findViewById(R.id.handle);
        clear = findViewById(R.id.clear);
        finish = findViewById(R.id.finish);
        result = findViewById(R.id.result);
        handle.setOnClickListener(new DoSomeThing());
        clear.setOnClickListener(new DoSomeThing());
        finish.setOnClickListener(new DoSomeThing());

    }

    protected class DoSomeThing implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.handle) {
                a = Integer.parseInt(inputA.getText()+"");
                b = Integer.parseInt(inputB.getText()+"");
                c = Integer.parseInt(inputC.getText()+"");
                double delta = Math.pow(b, 2)-4*a*c;
                if (delta<0) {
                    result.setText("Pt vô nghiệm");
                    return;
                }
                if (delta==0) {
                    result.setText("Pt có 1 nghiệm kép là: "+(-1*b/(2*a)));
                    return;
                }
                result.setText("Pt có 2 No: x1="+((-1*b+Math.sqrt(delta))/(2*a))+"; x2="+((-1*b-Math.sqrt(delta))/(2*a)));
                return;
            }

            if (view.getId() == R.id.clear) {
                inputA.setText(" ");
                inputB.setText(" ");
                inputC.setText(" ");
            }

            if (view.getId() == R.id.finish) {
                finish();
            }
        }
    }
}

