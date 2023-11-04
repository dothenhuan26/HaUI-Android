package com.example.bai24c_49_dothenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText inputHeigh, inputWeigh, result, diagnostic;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == button) {
                double res = (Integer.parseInt(inputWeigh.getText() + "")) / (Math.pow((Double.parseDouble(inputHeigh.getText() + "")), 2));
                res = Math.round(res * 10.0) / 10.0;
                result.setText(res + "");
                diagnostic.setText(check(res));
            }
        }

        public String check(Double res) {
            if (res < 18) return "Người gầy";
            if (res < 24.9 && res > 18) return "Người bình thường";
            if (res > 25 && res < 29.9) return "Người béo phì độ 1";
            if (res > 30 && res < 34.9) return "Người béo phì độ 2";
            return "Người béo phì độ 3";
        }

        ;


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        button.setOnClickListener(listener);
    }

    private void getWidget() {
        button = (Button) findViewById(R.id.button);
        inputHeigh = (EditText) findViewById(R.id.inputHeigh);
        inputWeigh = (EditText) findViewById(R.id.inputWeight);
        result = (EditText) findViewById(R.id.outputResult);
        diagnostic = (EditText) findViewById(R.id.outputDiagnostic);
    }
}