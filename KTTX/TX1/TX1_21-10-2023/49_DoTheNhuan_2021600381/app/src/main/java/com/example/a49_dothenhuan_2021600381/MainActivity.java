package com.example.a49_dothenhuan_2021600381;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Calendar cal;

    String arr[] = {"Chon ngay", "Thu 2", "Thu 3", "Thu 4", "Thu 5", "Thu 6", "Thu 7", "Chu nhat"};


    int pos = -1;
    EditText name, time, dateTxt;
    Spinner date;

    String dateContent="";

    Button btnTime, btnAdd, btnUpdate;
    CheckBox loop;
    RadioGroup grBuoi;
    RadioButton radSang, radChieu;
    ListView lv;

    ArrayList<BaoThuc> bts;
    ArrayAdapter<BaoThuc> adapter;

    ArrayAdapter<String> adapterDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWidget();

        setEventItem();
        btnTime.setOnClickListener(new DoSomething());
        btnAdd.setOnClickListener(new DoSomething());
        btnUpdate.setOnClickListener(new DoSomething());
    }

    public void getDateSpinner() {
        String arr[] = {"Thu 2", "Thu 3", "Thu 4", "Thu 5", "Thu 6", "Thu 7", "Chu nhat"};



    }


    public void getWidget() {
        name = findViewById(R.id.name);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        btnAdd = findViewById(R.id.btnAdd);
        btnTime = findViewById(R.id.btnTime);
        btnUpdate = findViewById(R.id.btnUpdate);
        lv = findViewById(R.id.lv);
        loop = findViewById(R.id.loop);
        grBuoi = findViewById(R.id.grBuoi);
        radSang = findViewById(R.id.radSang);
        radChieu = findViewById(R.id.radChieu);
        dateTxt = findViewById(R.id.dateTxt);



        bts = new ArrayList<BaoThuc>();
        adapter = new ArrayAdapter<BaoThuc>(MainActivity.this, android.R.layout.simple_list_item_1, bts);
        lv.setAdapter(adapter);

        cal = Calendar.getInstance();

        adapterDate = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, arr);
        adapterDate.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        date.setAdapter(adapterDate);
        date.setOnItemSelectedListener(new MyProcessEvent());


    }


    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            dateContent = arr[position];
            dateTxt.setText(dateContent);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(MainActivity.this, "Bat buoc phai chon ngay", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void clearData() {
        name.setText("");
        time.setText("");
        loop.setChecked(false);
        grBuoi.clearCheck();
    }

    public void getTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                String s = hourOfDay+":"+minute;
                int hourTmp = hourOfDay;
                if (hourTmp > 12) hourTmp = hourTmp - 12;
                time.setText(hourTmp + ":" + minute);
            }
        };
        Date timeDefault = cal.getTime();
        TimePickerDialog time = new TimePickerDialog(MainActivity.this, callback, timeDefault.getHours(), timeDefault.getMinutes(), true);
        time.setTitle("Chon gio");
        time.show();
    }

    public void setEventItem() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clearData();
                pos = position;
                BaoThuc bt = bts.get(position);
                name.setText(bt.getName());

                dateTxt.setText(bt.getDate());

                time.setText(bt.getTime());
                String txtLoop = bt.getLoop();
                if (txtLoop.equals("Lap lai")) {
                    loop.setChecked(true);
                }
                String buoi = bt.getBuoi();
                if (buoi.equals("Sang")) {
                    radSang.setChecked(true);
                }
                if (buoi.equals("Chieu")) {
                    radChieu.setChecked(true);
                }
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Xoa bao thuc");
                b.setMessage("Co muon xoa bao thuc?");
                b.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.setNegativeButton("Dong y", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bts.remove(position);
                        adapter.notifyDataSetChanged();
                        clearData();
                    }
                });
                b.create().show();

                return false;
            }
        });
    }


    public class DoSomething implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.btnTime) {
                getTimePickerDialog();
            }

            if (v.getId() == R.id.btnUpdate) {
                if (pos == -1) {
                    Toast.makeText(MainActivity.this, "Bao thuc khong hop le!", Toast.LENGTH_LONG).show();
                    return;
                }
                BaoThuc bt = bts.get(pos);

                String txtName = name.getText() + "";
                if (txtName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ten bao thuc khong duoc de trong", Toast.LENGTH_LONG).show();
                    return;
                }

                if (dateContent.equals("Chon ngay")) {
                    Toast.makeText(MainActivity.this, "Phai chon ngay bao thuc", Toast.LENGTH_LONG).show();
                    return;
                }

                String txtTime = time.getText() + "";
                if (txtTime.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Gio khong duoc de trong", Toast.LENGTH_LONG).show();
                    return;
                }
                String txtLoop = "";
                if (loop.isChecked()) {
                    txtLoop = loop.getText() + "";
                }

                int chkGrBuoi = grBuoi.getCheckedRadioButtonId();
                if (chkGrBuoi == -1) {
                    Toast.makeText(MainActivity.this, "Phai chon buoi bao thuc (Sang/Chieu)", Toast.LENGTH_LONG).show();
                    return;
                }
                RadioButton chkBuoi = findViewById(chkGrBuoi);
                String txtBuoi = chkBuoi.getText() + "";

                bt.setName(txtName);
                bt.setTime(txtTime);
                bt.setLoop(txtLoop);
                bt.setBuoi(txtBuoi);
                bt.setDate(dateContent);

                adapter.notifyDataSetChanged();
                pos = -1;
                clearData();
            }

            if (v.getId() == R.id.btnAdd) {
                String txtName = name.getText() + "";
                if (txtName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ten bao thuc khong duoc de trong", Toast.LENGTH_LONG).show();
                    return;
                }

                if (dateContent.equals("Chon ngay")) {
                    Toast.makeText(MainActivity.this, "Phai chon ngay bao thuc", Toast.LENGTH_LONG).show();
                    return;
                }

                String txtTime = time.getText() + "";
                if (txtTime.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Gio khong duoc de trong", Toast.LENGTH_LONG).show();
                    return;
                }
                String txtLoop = "";
                if (loop.isChecked()) {
                    txtLoop = loop.getText() + "";
                }

                int chkGrBuoi = grBuoi.getCheckedRadioButtonId();
                if (chkGrBuoi == -1) {
                    Toast.makeText(MainActivity.this, "Phai chon buoi bao thuc (Sang/Chieu)", Toast.LENGTH_LONG).show();
                    return;
                }
                RadioButton chkBuoi = findViewById(chkGrBuoi);
                String txtBuoi = chkBuoi.getText() + "";

                BaoThuc bt = new BaoThuc(txtName, dateContent, txtTime, txtLoop, txtBuoi);
                bts.add(bt);
                adapter.notifyDataSetChanged();
                clearData();
            }
        }
    }

}