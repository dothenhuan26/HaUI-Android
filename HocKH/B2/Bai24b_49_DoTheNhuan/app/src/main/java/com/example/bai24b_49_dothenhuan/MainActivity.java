package com.example.bai24b_49_dothenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button convert;
    EditText inputYear;
    TextView result;

    String can[] = {"Canh", "Tân", "Nhân", "Qúy", "Gíap", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
    String chi[] = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sử", "Dần", "Mão", "Thì", "Tỵ", "Ngọ", "Mùi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convert = (Button) findViewById(R.id.convert);
        inputYear = (EditText) findViewById(R.id.inputYear);
        result = (TextView) findViewById(R.id.outputResult);
        convert.setOnClickListener(new DoSomeThing());
    }


    protected class DoSomeThing implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.convert) {
                int year = Integer.parseInt(inputYear.getText() + "");
                result.setText("" + can[year % 10] +" "+ chi[year % 12]);
            }
        }
    }

}