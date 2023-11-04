package com.example.pizzaorder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Date lc = Calendar.getInstance().getTime();
    LocalDateTime lcdt = LocalDateTime.now();
    SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
    EditText inputName, inputPhone, inputDate;
    Button buttonDate, submit;
    RadioGroup grType, grShape;
    RadioButton radRound;
    CheckBox chk1, chk2, chk3, chk4;
    ListView lvBill;
    ArrayList<Bill> bills;
    ArrayAdapter<Bill> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        init();
        buttonDate.setOnClickListener(new Handle());
        submit.setOnClickListener(new Handle());
        chooseItem();
    }

    public void getWidget() {
        inputName = (EditText) findViewById(R.id.inputName);
        inputPhone = (EditText) findViewById(R.id.inputPhone);
        inputDate = (EditText) findViewById(R.id.inputDate);
        buttonDate = (Button) findViewById(R.id.buttonDate);
        submit = (Button) findViewById(R.id.submit);
        chk1 = (CheckBox) findViewById(R.id.chk1);
        chk2 = (CheckBox) findViewById(R.id.chk2);
        chk3 = (CheckBox) findViewById(R.id.chk3);
        chk4 = (CheckBox) findViewById(R.id.chk4);
        grType = (RadioGroup) findViewById(R.id.radType);
        grShape = (RadioGroup) findViewById(R.id.radShape);
        radRound = (RadioButton) findViewById(R.id.radRound);
        lvBill = (ListView) findViewById(R.id.lvBill);
        bills = new ArrayList<Bill>();
        adapter = new ArrayAdapter<Bill>(MainActivity.this, android.R.layout.simple_list_item_1, bills);
        lvBill.setAdapter(adapter);
    }
    public void init() {
        inputDate.setText(lcdt.getDayOfMonth() + "/" + lcdt.getMonthValue() + "/" + lcdt.getYear());
        radRound.setChecked(true);
    }
    public void clearData() {
        inputName.setText("");
        inputPhone.setText("");
        inputDate.setText(lcdt.getDayOfMonth() + "/" + lcdt.getMonthValue() + "/" + lcdt.getYear());
        radRound.setChecked(true);
        grType.clearCheck();
        chk1.setChecked(false);
        chk2.setChecked(false);
        chk3.setChecked(false);
        chk4.setChecked(false);
    }
    public void getToppingContent() {
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
    }
    public void displayData(Bill bill) {
        inputName.setText(bill.getName());
        inputPhone.setText(bill.getPhone());
        inputDate.setText(bill.getDateFormat());
        String type = bill.getType();
        for (int i = 0; i < grType.getChildCount(); i++) {
            RadioButton current = (RadioButton) grType.getChildAt(i);
            if (current.getText().equals(type)) {
                current.setChecked(true);
            }
        }

        ArrayList<String> topping = new ArrayList<String>(Arrays.asList(bill.getTopping()));
        getToppingContent();
        if (topping.contains(chk1.getText())) {
            chk1.setChecked(true);
        }
        if (topping.contains(chk2.getText())) {
            chk2.setChecked(true);
        }
        if (topping.contains(chk3.getText())) {
            chk3.setChecked(true);
        }
        if (topping.contains(chk4.getText())) {
            chk4.setChecked(true);
        }
    }
    public void chooseItem() {
        lvBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clearData();
                Bill bill = bills.get(position);
                displayData(bill);
            }
        });
        lvBill.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                bills.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
    public void getDatePicker() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                inputDate.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };
        String s = inputDate.getText() + "";
        int day, month, year;
        if (s.isEmpty()) {
            day = lc.getDay();
            month = lc.getMonth();
            year = lc.getYear();
        } else {
            String dates[] = s.trim().split("/");
            day = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            year = Integer.parseInt(dates[2]);
        }
        DatePickerDialog pic = new DatePickerDialog(MainActivity.this, callback, 2023, 10, 13);
        pic.setTitle("Enter Date");
        pic.show();
    }
    public class Handle implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buttonDate) {
                getDatePicker();
            }
            if (v.getId() == R.id.submit) {
                String name = inputName.getText() + "";
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ten khong duoc de trong!", Toast.LENGTH_LONG).show();
                    return;
                }
                String phone = inputPhone.getText() + "";
                if (phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "So dien thoai khong duoc de trong!", Toast.LENGTH_LONG).show();
                    return;
                }
                String date = inputDate.getText() + "";
                if (date.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ngay dat khong duoc de trong!", Toast.LENGTH_LONG).show();
                    return;
                }
                int type = grType.getCheckedRadioButtonId();
                int shape = grShape.getCheckedRadioButtonId();
                RadioButton radType, radShape;
                String topping="";

                if (chk1.isChecked()) {
                    topping+=(chk1.getText() + ",");
                }
                if (chk2.isChecked()) {
                    topping+=(chk2.getText() + ",");
                }
                if (chk3.isChecked()) {
                    topping+=(chk3.getText() + ",");
                }
                if (chk4.isChecked()) {
                    topping+=(chk4.getText() + ",");
                }

                if (type == -1) {
                    Toast.makeText(MainActivity.this, "Bat buoc phai chon loai pizza!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (shape == -1) {
                    Toast.makeText(MainActivity.this, "Bat buoc phai chon hinh dang pizza!!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (topping.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bat buoc phai chon topping!", Toast.LENGTH_LONG).show();
                    return;
                }
                radType = findViewById(type);
                radShape = findViewById(shape);
                Date dateOrder = null;
                try {
                    dateOrder = dft.parse(date);
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(), "Thoi gian khong hop le", Toast.LENGTH_LONG).show();
                    return;
                }
                Bill bill = new Bill(name, phone, dateOrder, radType.getText() + "", radShape.getText() + "", topping.split(","));
                bills.add(bill);
                adapter.notifyDataSetChanged();
                clearData();
            }
        }
    }


}