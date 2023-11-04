package com.example.bai46_49_dothenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bai46_49_dothenhuan.NhanVien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText inputId, inputName;
    Button button;

    int index = -1;

    RadioGroup radioGroup;
    ListView lv;

    ArrayList<NhanVien> nv;

    ArrayAdapter<NhanVien> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();

        nv = new ArrayList<NhanVien>();
        NhanVien em = new NhanVien("01", "Nhuan", true);
        nv.add(em);
        adapter = new ArrayAdapter<NhanVien>(this, android.R.layout.simple_list_item_1, nv);
        lv.setAdapter(adapter);

        button.setOnClickListener(new DoSomeThing());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien cnv = nv.get(position);
                inputId.setText(cnv.getId());
                inputName.setText(cnv.getName());
                if (cnv.isType()) {
                    RadioButton rad = findViewById(R.id.rad1);
                    rad.setChecked(true);
                }
                index = position;
            }
        });

    }

    protected class DoSomeThing implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                String id, name;
                boolean type;
                id = inputId.getText().toString().trim();
                name = inputName.getText().toString().trim();
                int radId = radioGroup.getCheckedRadioButtonId();

                if (id.isEmpty() || name.isEmpty() || radId == -1) {
                    Toast.makeText(MainActivity.this, "Khong duoc de trong thong tin!", Toast.LENGTH_LONG).show();
                    return;
                }

                RadioButton chkRad = findViewById(radId);
                if (chkRad == findViewById(R.id.rad1)) {
                    type = true;
                } else {
                    type = false;
                }
                NhanVien newnv;
                if (index==-1) {
                    newnv = new NhanVien(id, name, type);
                    index=-1;
                    nv.add(newnv);
                } else {
                    newnv = nv.get(index);
                    newnv.setId(id);
                    newnv.setName(name);
                    newnv.setType(type);
                }
                adapter.notifyDataSetChanged();
                inputId.setText("");
                inputName.setText("");
                radioGroup.clearCheck();
            }
        }
    }

    public void getWidget() {
        inputId = (EditText) findViewById(R.id.inputId);
        inputName = (EditText) findViewById(R.id.inputName);
        button = (Button) findViewById(R.id.button);
        radioGroup = findViewById(R.id.radGroup);
        lv = (ListView) findViewById(R.id.lvPerson);
    }
}