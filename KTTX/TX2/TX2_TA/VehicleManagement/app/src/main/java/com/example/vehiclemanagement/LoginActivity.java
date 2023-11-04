package com.example.vehiclemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    Intent main;

    public void getWidget() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWidget();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle();
            }
        });
    }

    public void handle() {
        String username_txt = username.getText() + "";
        if (username_txt.isEmpty()) {
            Toast.makeText(this, "Username is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        String password_txt = password.getText() + "";
        if (password_txt.isEmpty()) {
            Toast.makeText(this, "Password is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        main = new Intent(LoginActivity.this, MainActivity.class);
        main.putExtra("username", username_txt);
        main.putExtra("password", password_txt);
        startActivity(main);
    }

}