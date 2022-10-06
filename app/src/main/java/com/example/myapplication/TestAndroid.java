package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class TestAndroid extends AppCompatActivity {
    int sumScore = 0;
    RadioButton zq1;
    RadioButton zq2;
    RadioButton zq3;
    RadioButton zq4;
    CheckBox zq5;
    CheckBox zq6;
    CheckBox zq7;
    CheckBox zq8;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testandroid);

        Button submit;
        Button tc;

        zq1 = findViewById(R.id.zq1);
        zq2 = findViewById(R.id.zq2);
        zq3 = findViewById(R.id.zq3);
        zq4 = findViewById(R.id.zq4);
        zq5 = findViewById(R.id.zq5);
        zq6 = findViewById(R.id.zq6);
        zq7 = findViewById(R.id.zq7);
        zq8 = findViewById(R.id.zq8);
        submit = findViewById(R.id.submit);
        tc = findViewById(R.id.tc);
        submit.setOnClickListener(new myClick());
        tc.setOnClickListener(new myClick());
    }

    class myClick implements View.OnClickListener
    {

        public void onClick(View v)
        {
            switch (v.getId()) {
                case R.id.submit:
                if (zq1.isChecked()) {
                    sumScore += 20;
                }
                if (zq2.isChecked()) {
                    sumScore += 20;
                }
                if (zq3.isChecked()) {
                    sumScore += 20;
                }
                if (zq4.isChecked()) {
                    sumScore += 20;
                }

                if (zq5.isChecked() && zq6.isChecked() && zq7.isChecked() && zq8.isChecked()) {
                    sumScore += 20;
                }
                Toast toast = Toast.makeText(TestAndroid.this, "您得了" + sumScore + "分", Toast.LENGTH_SHORT);
                toast.show();
                    DBexam.getInstance(TestAndroid.this).updateUserExam("androidexam",
                            sumScore, Yonghu.name);
                sumScore = 0;
                finish();
                break;
                case R.id.tc:
                    finish();
                    break;
            }
        }

    }
}