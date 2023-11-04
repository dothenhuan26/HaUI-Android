package com.example.bai46;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtID, txtName;
    RadioGroup grType;
    Button add;

    ListView lv;
    ArrayList<Staff> staffs = null;
    ArrayAdapter<Staff> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        addEventFormWidget();
    }
    public void getFormWidgets() {
        txtID = findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtName);
        grType = findViewById(R.id.grType);
        add = findViewById(R.id.btbAdd);
        lv =findViewById(R.id.lv);
        staffs = new ArrayList<Staff>();
        adapter = new ArrayAdapter<Staff>(this, android.R.layout.simple_list_item_1, staffs);
        lv.setAdapter(adapter);
    }
    public void addEventFormWidget() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID, name, type;
                ID = txtID.getText().toString().trim();
                name = txtName.getText().toString().trim();
                int id = grType.getCheckedRadioButtonId();

                if (name.isEmpty() || ID.isEmpty() || id == -1) {
                    Toast.makeText(MainActivity.this, "Fill all the gap", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton rad = findViewById(id);
                type = rad.getText().toString();
                Staff staff = new Staff(ID, name, type);
                staffs.add(staff);
                adapter.notifyDataSetChanged();
                txtID.setText("");
                txtName.setText("");
                grType.clearCheck();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Staff staff = staffs.get(i);
                txtID.setText(staff.getID());
                txtName.setText(staff.getName());
                if(staff.getType().equals("Fulltime")) {
                    RadioButton rad = findViewById(R.id.radioFull);
                    rad.setChecked(true);
                }
                else {
                    RadioButton rad = findViewById(R.id.radioPart);
                    rad.setChecked(true);
                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("");
                builder.setMessage("Are you sure about that!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        staffs.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

}