package com.example.bai86_49_dothenhuan_2021600381;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText product_id, product_name, product_amount;
    ListView lv;
    ArrayList<Product> list = new ArrayList<Product>();
    ArrayList<String> categories = new ArrayList<String>();
    ArrayAdapter<Product> adapter;
    ArrayAdapter<String> catAdapter;
    Product product;
    int pos = -1, postCat = -1;
    String cat = "";
    String txtFile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fakeData();
        getWidget();
        getSpinnerSelected();
        setEventForList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void fakeData() {
        categories.add("--Choose--");
        categories.add("SamSung");
        categories.add("Nokia");
        categories.add("Apple");
        categories.add("LG");

//        list.add(new Product("001", "Nokia 01", "Nokia", 20));
//        list.add(new Product("002", "SamSung 01", "SamSung", 120));
//        list.add(new Product("003", "Apple 01", "Apple", 230));
//        list.add(new Product("004", "LG 01", "LG", 420));

    }

    public void getWidget() {
        spinner = findViewById(R.id.spinner);
        product_id = findViewById(R.id.product_id);
        product_name = findViewById(R.id.product_name);
        product_amount = findViewById(R.id.product_amount);
        lv = findViewById(R.id.lv);
        adapter = new ArrayAdapter<Product>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        catAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(catAdapter);
        spinner.setSelection(0);
    }

    public void clearData() {
        product_id.setText("");
        product_name.setText("");
        product_amount.setText("");
        spinner.setSelection(0);
        pos = -1;
        postCat = -1;
        cat = "";
        txtFile = "";
        product = null;
    }

    public void setSpinnerChecked(String cat) {
        if (cat.equals("SamSung")) {
            spinner.setSelection(1);
        }
        if (cat.equals("Nokia")) {
            spinner.setSelection(2);
        }
        if (cat.equals("Apple")) {
            spinner.setSelection(3);
        }
        if (cat.equals("LG")) {
            spinner.setSelection(4);
        }
    }

    public void setEventForList() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                product = list.get(position);
                setSpinnerChecked(product.getCategory());
                product_id.setText(product.getId());
                product_name.setText(product.getName());
                product_amount.setText(product.getAmount() + "");
            }
        });
    }

    public void getSpinnerSelected() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                postCat = position;
//                cat = categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int choosen = item.getItemId();

        if (choosen == R.id.add_product) {
            if (postCat < 1) {
                Toast.makeText(this, "Danh mục bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String catTxt = categories.get(postCat);
            String product_id_txt = product_id.getText() + "";
            if (product_id_txt.isEmpty()) {
                Toast.makeText(this, "Mã sản phẩm bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String product_name_txt = product_name.getText() + "";
            if (product_name_txt.isEmpty()) {
                Toast.makeText(this, "Tên sản phẩm bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String product_amount_txt = product_amount.getText() + "";
            if (((String) product_amount_txt).isEmpty()) {
                Toast.makeText(this, "Số lượng sản phẩm bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            int product_amount_int = Integer.parseInt(product_amount_txt);
            list.add(new Product(product_id_txt, product_name_txt, catTxt, product_amount_int));
            adapter.notifyDataSetChanged();
            clearData();
        }

        if (choosen == R.id.update_product) {
            if (postCat < 1) {
                Toast.makeText(this, "Danh mục bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String catTxt = categories.get(postCat);
            String product_id_txt = product_id.getText() + "";
            if (product_id_txt.isEmpty()) {
                Toast.makeText(this, "Mã sản phẩm bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String product_name_txt = product_name.getText() + "";
            if (product_name_txt.isEmpty()) {
                Toast.makeText(this, "Tên sản phẩm bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            String product_amount_txt = product_amount.getText() + "";
            if (((String) product_amount_txt).isEmpty()) {
                Toast.makeText(this, "Số lượng sản phẩm bắt buộc phải chọn!", Toast.LENGTH_SHORT).show();
                return false;
            }
            int product_amount_int = Integer.parseInt(product_amount_txt);
            product = list.get(pos);
            product.setCategory(catTxt);
            product.setId(product_id_txt);
            product.setName(product_name_txt);
            product.setAmount(product_amount_int);
            adapter.notifyDataSetChanged();
            clearData();
        }

        if (choosen == R.id.delete_product) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Chắc chắn xóa?");
            builder.setTitle("Xóa sản phẩm");
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    list.remove(pos);
                    adapter.notifyDataSetChanged();
                    clearData();
                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        if (choosen == R.id.save_to_file) {
            String sdCard = this.getExternalFilesDir(null).getAbsolutePath() + "/myfile.txt";
            try {
                File file = new File(sdCard);
                OutputStream os = new FileOutputStream(file);
                String str = "";
                list.forEach((n) -> {
                    txtFile += (n.toString() + "\n");
                });

                os.write(txtFile.getBytes());
                os.close();
            } catch (Exception e) {
            }
        }

        if (choosen == R.id.read_from_file) {
            String sdCard = this.getExternalFilesDir(null).getAbsolutePath() + "/myfile.txt";
            File file = new File(sdCard);
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append("\n");

                    String[] str = line.trim().split("-");
                    list.add(new Product(str[0], str[1], str[2], Integer.parseInt(str[3])));
                }
                adapter.notifyDataSetChanged();
                br.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        if (choosen == R.id.close) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}