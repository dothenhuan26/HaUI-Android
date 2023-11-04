package com.example.a49_dothenhuan_2021600381;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText day, soKm;
    RadioGroup grRad;
    RadioButton radSang, radChieu;
    TextView fullname, totalKm;
    Button submit;
    int pos = -1;
    int total = 0;

    ArrayList<String> days = new ArrayList<String>();

    ArrayAdapter<String> adapterDays;

    ArrayList<LuyenTap> list = new ArrayList<LuyenTap>();
    ArrayAdapter<LuyenTap> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDays();

        getWidget();

        registerForContextMenu(submit);

        setEventSubmit();
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int choosen = item.getItemId();
        if (choosen == R.id.add) {
            String ngayTxt = day.getText() + "";
            if (ngayTxt.isEmpty()) {
                Toast.makeText(this, "Ngày trong tuần không được để trống!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String sokmTxt = soKm.getText() + "";
            if (sokmTxt.isEmpty()) {
                Toast.makeText(this, "Số km không được để trống!", Toast.LENGTH_SHORT).show();
                return false;
            }
            int sokmInt = Integer.parseInt(sokmTxt);

            int grChecked = grRad.getCheckedRadioButtonId();
            String buoi = "";
            if (grChecked == R.id.radSang) {
                buoi = radSang.getText() + "";
            } else if (grChecked == R.id.radChieu) {
                buoi = radChieu.getText() + "";
            } else {
                Toast.makeText(this, "Buổi tập không được để trống!", Toast.LENGTH_SHORT).show();
                return false;
            }
            total += sokmInt;
            totalKm.setText(total + "");
            LuyenTap lt = new LuyenTap(ngayTxt, sokmInt, buoi);
            list.add(lt);
        }

        if (choosen == R.id.save) {
//            String sdcard= this.getExternalFilesDir(null).
//                    getAbsolutePath()+"/myFile.txt";
//            try {
//                OutputStreamWriter writer= new OutputStreamWriter( new FileOutputStream(sdcard));
//                list.forEach(luyenTap -> {
//                    try {
//                        writer.write(luyenTap.toString());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//                writer.close();
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

            String fileName ="myfile.txt";
            try {
                FileOutputStream out = openFileOutput(fileName, MODE_PRIVATE);
                OutputStreamWriter writer= new OutputStreamWriter(out);
                list.forEach(luyenTap -> {
                    try {
                        writer.write(luyenTap.toString());
                        writer.append("\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                writer.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


        return super.onContextItemSelected(item);
    }

    public void setEventSubmit() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2 = new Intent(MainActivity.this, Activity2.class);

                ArrayList<String> listTxt = new ArrayList<String>();
                list.forEach(luyenTap -> {
                    listTxt.add(luyenTap.toString());
                });

//                activity2.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
                activity2.putStringArrayListExtra("list", listTxt);
                startActivity(activity2);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day.setText(days.get(position));
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
            }
        });


    }

    public void setDays() {
//        days.add("Chọn ngày");
        days.add("Thứ 2");
        days.add("Thứ 3");
        days.add("Thứ 4");
        days.add("Thứ 5");
        days.add("Thứ 6");
        days.add("Thứ 7");
        days.add("Chủ nhật");
    }

    public void getWidget() {
        submit = findViewById(R.id.submit);
        spinner = findViewById(R.id.spinner);
        day = findViewById(R.id.day);
        soKm = findViewById(R.id.soKm);
        grRad = findViewById(R.id.grRad);
        fullname = findViewById(R.id.fullname);
        totalKm = findViewById(R.id.totalKm);
        radSang = findViewById(R.id.radSang);
        radChieu = findViewById(R.id.radChieu);
        adapterDays = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, days);
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterDays);
        spinner.setSelection(0);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}