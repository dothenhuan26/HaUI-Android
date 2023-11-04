package com.example.vehiclemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView username_txt;
    EditText manufacture_txt, carname_txt;
    Intent login;
    Spinner spinner;
    ListView lv;
    ArrayAdapter adapter, spinAdaper;
    ArrayList<Car> list;
    ArrayList<String> manufactures;
    int pos = -1;

    public void fakeData() {
        manufactures.add("Choose");
        manufactures.add("Honda");
        manufactures.add("Toyota");
        manufactures.add("Chevrolet");
        manufactures.add("Ford");

        Car car1 = new Car("Accord", "Honda");
        Car car2 = new Car("Corolla", "Toyota");
        list.add(car1);
        list.add(car2);


    }

    public void getWidget() {
        username_txt = findViewById(R.id.username_txt);
        manufacture_txt = findViewById(R.id.manufacture_txt);
        carname_txt = findViewById(R.id.carname_txt);
        login = getIntent();
        username_txt.setText(login.getStringExtra("username"));

        lv = findViewById(R.id.lv);
        list = new ArrayList<Car>();
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        spinner = findViewById(R.id.spinner);
        manufactures = new ArrayList<String>();
        fakeData();
        spinAdaper = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, manufactures);
        spinAdaper.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinAdaper);
        spinner.setSelection(0);
    }

    public void spinnerHandle() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                manufacture_txt.setText(manufactures.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        spinnerHandle();
        registerForContextMenu(lv);
        lvHandle();

    }

    public void setSelectSpinner(String manufacture) {
        if (manufacture.equals("Honda")) {
            spinner.setSelection(1);
        }
        if (manufacture.equals("Toyota")) {
            spinner.setSelection(2);
        }
        if (manufacture.equals("Chevrolet")) {
            spinner.setSelection(3);
        }
        if (manufacture.equals("Ford")) {
            spinner.setSelection(4);
        }


    }

    public void lvHandle() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                Car car = list.get(position);
                carname_txt.setText(car.getCarname());
                manufacture_txt.setText(car.getManufacture());
                setSelectSpinner(manufacture_txt.getText() + "");
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_entry) {
            String carnameTxt = carname_txt.getText() + "";
            if (carnameTxt.isEmpty()) {
                Toast.makeText(this, "Car name is required!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String carmanufactureTxt = manufacture_txt.getText() + "";
            if (carmanufactureTxt.isEmpty()) {
                Toast.makeText(this, "Car manufacture is required!", Toast.LENGTH_SHORT).show();
                return false;
            }


            Car car = new Car(carnameTxt, carmanufactureTxt);
            list.add(car);
            adapter.notifyDataSetChanged();
        }


        if (item.getItemId() == R.id.update_entry) {
            Car car = list.get(pos);
            String carnameTxt = carname_txt.getText() + "";
            if (carnameTxt.isEmpty()) {
                Toast.makeText(this, "Car name is required!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String carmanufactureTxt = manufacture_txt.getText() + "";
            if (carmanufactureTxt.isEmpty()) {
                Toast.makeText(this, "Car manufacture is required!", Toast.LENGTH_SHORT).show();
                return false;
            }
            car.setCarname(carnameTxt);
            car.setManufacture(carmanufactureTxt);
            adapter.notifyDataSetChanged();

        }

        if (item.getItemId() == R.id.delete_entry) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Chac chan xoa?");
            builder.setTitle("Xoa");
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(pos);
                    adapter.notifyDataSetChanged();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }


        return super.onContextItemSelected(item);
    }
}