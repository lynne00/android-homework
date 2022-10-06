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

public class Xiugaipassword extends AppCompatActivity {
    public DBsqlite dBsqlite;
    public  String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.xiugaipassword);
        Button xyb = findViewById(R.id.xyb);
        Button fh = findViewById(R.id.fh);
        Button qk=findViewById(R.id.qk);


        xyb.setOnClickListener(new myclick());
        fh.setOnClickListener(new myclick());
        qk.setOnClickListener(new myclick());
        dBsqlite= new DBsqlite(this);
        Bundle bundle=this.getIntent().getExtras();
        name=bundle.getString("username");

    }

    class myclick implements View.OnClickListener {
        EditText upassword= findViewById(R.id.password);

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.qk:
                    upassword.setText("");
                    break;
                case R.id.fh:
                    finish();
                    break;
                case R.id.xyb:
                    String password = upassword.getText().toString().trim();
                    if (!TextUtils.isEmpty(password)) {
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
                        if(match)
                       {
                           Bundle bundle = new Bundle();
                           bundle.putInt("flag", 4);
                           bundle.putString("title", "修改密码");
                           bundle.putString("content",  "" );
                           Toast.makeText(Xiugaipassword.this, "修改密码", Toast.LENGTH_SHORT).show();
                           Intent intent3 = new Intent(Xiugaipassword.this,Xiugai.class);
                           intent3.putExtras(bundle);
                           startActivity(intent3);
                           finish();
                        } else {
                            Toast.makeText(Xiugaipassword.this, "密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Xiugaipassword.this, "请输入你的密码", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

        }
    }
}
