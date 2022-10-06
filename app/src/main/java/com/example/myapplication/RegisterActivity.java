package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private DBsqlite dBsqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button zc = findViewById(R.id.zc);
        Button fh = findViewById(R.id.fh);

        zc.setOnClickListener(new myclick());
        fh.setOnClickListener(new myclick());
        dBsqlite= new DBsqlite(this);
    }
    class myclick implements View.OnClickListener {
        EditText uname= findViewById(R.id.et_name);
        EditText upassword1= findViewById(R.id.et_password1);
        EditText upassword2= findViewById(R.id.et_password2);
        User user;

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.zc:
                    String name = uname.getText().toString().trim();
                    String password1 = upassword1.getText().toString().trim();
                    String password2 = upassword2.getText().toString().trim();
                    //注册验证
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2) ) {
                        if (password1.equals(password2)) {
                            ArrayList<User> data = dBsqlite.getAllData();
                            for (int i = 0; i < data.size(); i++) {
                                user = data.get(i);
                            }
                            if (name.equals(user.getName())) {
                                Toast.makeText(RegisterActivity.this,  "该账号已被注册", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            dBsqlite.add(name, password1);
                            Toast.makeText(RegisterActivity.this,  "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "两次输入密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.fh:
                    Intent intent2 = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent2);
                    finish();
                    break;
            }

        }
    }
}
