package com.example.bai45_49_dothenhuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //khai báo các control dùng trong chương trình
    EditText txtTen;
    TextView txtSelection;
    Button btn;
    ListView lv;
    //khai báo arrayList
    ArrayList<String> arrList = null;
    //tùy biến theo adapter mặc định
    //tùy biến theo adapter mới
    MyAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //kết nối các control với Activity
        txtTen = (EditText) findViewById(R.id.editTen);
        txtSelection = (TextView) findViewById(R.id.txtSelection);
        lv = (ListView) findViewById(R.id.lvPerson);
        //1. tạo 1 arraylisst object
        arrList = new ArrayList<String>();
        arrList.add("Hà nội");
        arrList.add("Hải phòng");
        arrList.add("Huế");
        arrList.add("Đà nẵng");
        //2. gán DataSource vào ArrayAdapter
        adapter = new MyAdapter(MainActivity.this,
                R.layout.myitemlist,
                arrList);
        //3. Gán adapter vào listView
        lv.setAdapter(adapter);
        btn = (Button) findViewById(R.id.btnNhap);
        //4. Xử lý sự kiện nhấn nút nhập
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                arrList.add(txtTen.getText() + " ");
                adapter.notifyDataSetChanged();
                txtTen.setText("");
            }
        });
        //5. Xử lý sự kiện chọn một phần tử trong listView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                txtSelection.setText("vị trí:" + arg2 + " giá trị:" + arrList.get(arg2));
                txtTen.setText(arrList.get(arg2) + "");
            }
        });
        //6, Xử lý sự kiện LongClick
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                arrList.remove(arg2);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}



