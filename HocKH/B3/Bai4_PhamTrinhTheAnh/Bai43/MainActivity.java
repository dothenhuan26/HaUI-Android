package com.example.bai43;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnCalc,btnClose;
    EditText name,height,weight;
    TextView result, resultBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc=findViewById(R.id.btnCalc);
        btnClose=findViewById(R.id.btnClose);
        name=findViewById(R.id.name);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        resultBMI=findViewById(R.id.resultBMI);
        result=findViewById(R.id.result);

        btnCalc.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == btnCalc) {
            try {
                double H = Double.parseDouble(height.getText().toString());
                double W = Double.parseDouble(weight.getText().toString());
                double BMI = W / Math.pow(H, 2);
                String Result = "";
                if (BMI < 18) Result = "Skinny";
                else if (BMI <= 24.9) Result = "Normal";
                else if (BMI <= 29.9) Result = "Fat 1";
                else if (BMI <= 34.9) Result = "Fat 2";
                else Result = "Fat 3";
                DecimalFormat dcf = new DecimalFormat("#.0");
                resultBMI.setText(dcf.format(BMI));
                result.setText(Result);
            } catch (NumberFormatException e) {
                Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show();
                name.setText("");
                weight.setText("");
                height.setText("");
                resultBMI.setText("");
                result.setText("");
            }
        } else if (v == btnClose) {
            finish();
        }
    }

}
