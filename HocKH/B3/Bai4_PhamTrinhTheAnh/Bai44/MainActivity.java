package com.example.bai44;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //khai báo các control
    EditText editTen,editCMND,editBoSung;
    CheckBox chkDocBao,chkDocSach,chkDocCode;
    Button btnGuiTT,btnDong;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWidget();
        btnGuiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowInformation();
            }

        });
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Notification");
                builder.setMessage("Do you want to exit");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                });
                builder.create().show();
            }
        });

    }
    public void getWidget(){
        editTen=findViewById(R.id.editHoTen);
        editCMND=findViewById(R.id.editCMND);
        editBoSung=findViewById(R.id.editBoSung);
        chkDocBao=findViewById(R.id.chkDocBao);
        chkDocSach=findViewById(R.id.chkDocSach);
        chkDocCode=findViewById(R.id.chkDocCoding);
        btnGuiTT=findViewById(R.id.btnGuiTT);
        btnDong=findViewById(R.id.btnClose);
        group=findViewById(R.id.radioGroup1);
    }
    public String getName(){

        String ten=editTen.getText()+"";
        ten=ten.trim();
        if (ten.length()<3) {
            editTen.requestFocus();
            editTen.selectAll();
            Toast.makeText(this, "Name must be longer than 3 character",
                    Toast.LENGTH_LONG).show();
            return "";
        }
        return ten;
    }
    public String  getID(){
        String cmnd=editCMND.getText()+"";
        cmnd=cmnd.trim();
        if (cmnd.length()!=9) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "ID must contain 9 digit",
                    Toast.LENGTH_LONG).show();
            return "";
        }
        return cmnd;
    }
    public String getDegree(){
        String bangCap="";

        int id=group.getCheckedRadioButtonId();
        if (id==-1){
            Toast.makeText(this, "Choose degree",Toast.LENGTH_LONG).show();
            return "";
        }

        RadioButton rad=(RadioButton)findViewById(id);
        bangCap=rad.getText()+"";
        return  bangCap;
    }
    public String get_hobby(){
        String soThich="";
        if (chkDocBao.isChecked()) soThich+=chkDocBao.getText() +"\n";
        if (chkDocSach.isChecked()) soThich+=chkDocSach.getText() +"\n";
        if (chkDocCode.isChecked()) soThich+=chkDocCode.getText() +"\n";
        return  soThich;
    }


public void doShowInformation() {
    String ten = getName();
    String cmnd = getID();
    String bangCap = getDegree();
    String soThich = get_hobby();
    String boSung = editBoSung.getText().toString().trim();

    if (!ten.isEmpty() && !cmnd.isEmpty() && !bangCap.isEmpty()) {
        StringBuffer msg = new StringBuffer();
        msg.append("Name: ").append(ten).append("\n");
        msg.append("ID: ").append(cmnd).append("\n");
        msg.append("Degree: ").append(bangCap).append("\n");
        if (!soThich.isEmpty()) {
            msg.append("Hobbies:\n").append(soThich).append("\n");
        }
        msg.append("-------------------------------\n");
        msg.append("Additional information\n");
        msg.append(boSung).append("\n");
        msg.append("-------------------------------\n");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Notification");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "???", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setMessage(msg.toString());
        builder.create().show();
    } else {
        Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_LONG).show();
    }
}
}