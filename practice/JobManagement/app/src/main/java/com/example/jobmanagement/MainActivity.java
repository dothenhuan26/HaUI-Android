package com.example.jobmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Calendar cal=null;
    Date dateFinish, timeFinish;
    EditText inputJob, inputContent, inputDate, inputTime;
    Button btnDate, btnTime, submit;
    ListView lv;
    ArrayList<Job> jobs;
    ArrayAdapter<Job> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        setDefaultInfo();
        setEventItem();
        btnDate.setOnClickListener(new DoSomething());
        btnTime.setOnClickListener(new DoSomething());
        submit.setOnClickListener(new DoSomething());

    }

    public void getWidget() {
        inputJob = findViewById(R.id.inputJob);
        inputContent = findViewById(R.id.inputContent);
        inputDate = findViewById(R.id.inputDate);
        inputTime = findViewById(R.id.inputTime);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        submit = findViewById(R.id.submit);
        lv = findViewById(R.id.lv);
        jobs = new ArrayList<Job>();
        adapter = new ArrayAdapter<Job>(MainActivity.this, android.R.layout.simple_list_item_1, jobs);
        lv.setAdapter(adapter);
    }

    public void setEventItem() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clearData();
                getItem(position);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                clearData();
                deleteItem(position);
                return false;
            }
        });
    }


    public void getItem(int i) {
        Job job = jobs.get(i);
        inputJob.setText(job.getJob());
        inputContent.setText(job.getContent());
        inputDate.setText(job.getDateFormat(job.getDateFinish()));
        inputTime.setText(job.getTimeFormat(job.getTimeFinish()));
    }

    public void deleteItem(int i) {
        jobs.remove(i);
        adapter.notifyDataSetChanged();
        clearData();
        setDefaultInfo();
    }

    public void setDefaultInfo() {
        cal = Calendar.getInstance();
        SimpleDateFormat dft = null;
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        inputDate.setText(dft.format(cal.getTime()));
        dft = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        inputTime.setText(dft.format(cal.getTime()));
        dft = new SimpleDateFormat("HH:mm", Locale.getDefault());
        inputTime.setTag(dft.format(cal.getTime()));
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                inputDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                cal.set(year, month, dayOfMonth);
                dateFinish = cal.getTime();
            }
        };

        String txtDate = inputDate.getText() + "";
        String dateArr[] = txtDate.trim().split("/");
        int day = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]) - 1;
        int year = Integer.parseInt(dateArr[2]);

        DatePickerDialog pic = new DatePickerDialog(this, callback, year, month, day);
        pic.setTitle("Chon ngay!");
        pic.show();
    }

    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String s = hourOfDay + ":" + minute;
                int hourTmp = hourOfDay;
                if (hourTmp > 12) hourTmp = hourTmp - 12;
                inputTime.setText(hourTmp + ":" + minute + ((hourOfDay > 12) ? "PM" : "AM"));
                inputTime.setTag(s);
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                timeFinish = cal.getTime();
            }
        };

        String txtTime = inputTime.getTag() + "";
        String timeArr[] = txtTime.trim().split(":");
        int hours = Integer.parseInt(timeArr[0]);
        int minutes = Integer.parseInt(timeArr[1]);

        TimePickerDialog time = new TimePickerDialog(this, callback, hours, minutes, true);
        time.setTitle("Chon gio");
        time.show();
    }

    public void clearData() {
        inputJob.setText("");
        inputContent.setText("");
        inputTime.setText("");
        inputDate.setText("");
    }

    public class DoSomething implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnDate) {
                showDatePickerDialog();
            }
            if (v.getId() == R.id.btnTime) {
                showTimePickerDialog();
            }
            if (v.getId() == R.id.submit) {
                String job = inputJob.getText() + "";
                if (job.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Cong viec bat buoc phai nhap", Toast.LENGTH_LONG).show();
                    return;
                }
                String content = inputContent.getText() + "";
                if (content.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Noi dung bat buoc phai nhap", Toast.LENGTH_LONG).show();
                    return;
                }
                String date = inputDate.getText() + "";
                if (date.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ngay ket thuc bat buoc phai nhap", Toast.LENGTH_LONG).show();
                    return;
                }
                String time = inputTime.getText() + "";
                if (time.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Gio ket thuc bat buoc phai nhap", Toast.LENGTH_LONG).show();
                    return;
                }

                Job jobObj = new Job(job, content, dateFinish, timeFinish);
                jobs.add(jobObj);
                adapter.notifyDataSetChanged();
                clearData();
                setDefaultInfo();
            }
        }
    }

}