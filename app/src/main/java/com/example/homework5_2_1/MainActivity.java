package com.example.homework5_2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText loginEt;
    EditText passwordEt;
    Button loginBtn;
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEt = findViewById(R.id.login);
        passwordEt = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);
        regBtn = findViewById(R.id.registration_btn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(loginEt.getText().toString(), passwordEt.getText().toString());
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(loginEt.getText().toString(), passwordEt.getText().toString());
            }
        });

    }

    private void saveData(String login, String password){
        try{
            FileOutputStream fileOutputStream = openFileOutput(login, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            bw.write(password);
            bw.close();
        } catch (Exception e){

        }

    }

    private void check(String login, String password){
        try {
            FileInputStream fileInputStream = openFileInput(login);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            if (reader.readLine().equals(password)){
                Toast.makeText(MainActivity.this,
                        "Добро пожаловать " + login,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Не правильно введен пароль",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this,
                    "Не найдено пользователя с таким логином",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
