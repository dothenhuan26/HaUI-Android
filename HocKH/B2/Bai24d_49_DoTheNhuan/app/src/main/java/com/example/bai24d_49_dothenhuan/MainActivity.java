package com.example.bai24d_49_dothenhuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText inputName, inputCMND, inputSubInfo;
    Button button;

    CheckBox interest_1, interest_2, interest_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputName = findViewById(R.id.inputName);
        inputCMND = findViewById(R.id.inputCMND);
        inputSubInfo = findViewById(R.id.inputSubInfo);
        interest_1 = findViewById(R.id.interest_1);
        interest_2 = findViewById(R.id.interest_2);
        interest_3 = findViewById(R.id.interest_3);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowInformation();
            }
        });

    }

    public void doShowInformation() {
        String name = inputName.getText() + "";
        name = name.trim();
        if (name.length() < 3) {
            inputName.requestFocus();
            inputName.selectAll();
            Toast.makeText(this, "Tên phải lớn hơn 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String level = "";
        RadioGroup group = (RadioGroup) findViewById(R.id.levelGroup);
        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }

        RadioButton rad = (RadioButton) findViewById(id);
        level = rad.getText() + "";

        String likely = "";

        if (interest_1.isChecked()) {
            likely += interest_1.getText() + "\n";
        }
        if (interest_2.isChecked()) {
            likely += interest_2.getText() + "\n";
        }
        if (interest_3.isChecked()) {
            likely += interest_3.getText() + "\n";
        }

        String subInfo = inputSubInfo.getText()+"\n";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Dong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        String msg = name+"\n";
        msg+= inputCMND.getText()+"\n";
        msg+=level+"\n";
        msg+=likely+"\n";
        msg+="-------------------------\n";
        msg+="Thong tin bo sung\n";
        msg+=subInfo+"\n";
        msg+="-------------------------\n";
        builder.setMessage(msg);
        builder.create().show();


    }

}