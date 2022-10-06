package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public DBsqlite dBsqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button zc = findViewById(R.id.zc);
        Button dl = findViewById(R.id.dl);
        Button fh = findViewById(R.id.fh);

        zc.setOnClickListener(new myclick());
        dl.setOnClickListener(new myclick());
        fh.setOnClickListener(new myclick());
         dBsqlite= new DBsqlite(this);
    }

     class myclick implements View.OnClickListener {
        EditText uname= findViewById(R.id.et_name);
        EditText upassword= findViewById(R.id.et_password);
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.zc:
                    Intent intent =new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.fh:
                    System.exit(0);
                    break;
                case R.id.dl:
                    String name =uname.getText().toString().trim();
                    String password = upassword.getText().toString().trim();
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                        ArrayList<User> data = dBsqlite.getAllData();
                        boolean match = false;
                        for (int i = 0; i < data.size(); i++) {
                            User user = data.get(i);
                            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                                match = true;
                                break;
                            } else {
                                match = false;
                            }
                        }
                        if (match) {
                            Bundle bundle=new Bundle();
                            bundle.putString("username",name);
                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent3 = new Intent(MainActivity.this,Yonghu.class);
                            intent3.putExtras(bundle);
                            startActivity(intent3);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    }
}
