package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Xiugai extends AppCompatActivity {

    Button fh, bc,qk;
    TextView t_title;
    EditText et_content;
    String title, content,name;
    int flag;
    public DBsqlite dBsqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiugai);
        Bundle bundle=this.getIntent().getExtras();
        fh = findViewById(R.id.fh0);
        bc = findViewById(R.id.bc);
        qk=findViewById(R.id.qk);
        t_title=findViewById(R.id.title);
        et_content=findViewById(R.id.content);

        fh.setOnClickListener(new Xiugai.myclick());
        bc.setOnClickListener(new Xiugai.myclick());
        qk.setOnClickListener(new Xiugai.myclick());
        title=bundle.getString("title");
        content=bundle.getString("content");
        flag=bundle.getInt("flag");
        name=bundle.getString("username");
        t_title.setText(title);

        if (!TextUtils.isEmpty(content)){
            et_content.setText(content);
        }
    }

    class myclick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.qk:
                    et_content.setText("");
                    break;
                case R.id.fh0:
                    finish();
                    break;
                case R.id.bc:
                Intent data = new Intent();
                String etContent = et_content.getText().toString().trim();
                switch (flag) {
                    case 1:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("nickName", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(Xiugai.this, "保存成功", Toast.LENGTH_SHORT).show();
                            DBinfo.getInstance(Xiugai.this).updateUserInfo("nickName",
                                    etContent, name);
                            finish();
                        } else {
                            Toast.makeText(Xiugai.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("sex", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(Xiugai.this, "保存成功", Toast.LENGTH_SHORT).show();
                            DBinfo.getInstance(Xiugai.this).updateUserInfo("sex",
                                    etContent, name);
                            finish();
                        } else {
                            Toast.makeText(Xiugai.this, "性别不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if (!TextUtils.isEmpty(etContent)) {
                            data.putExtra("signature", etContent);
                            setResult(RESULT_OK, data);
                            Toast.makeText(Xiugai.this, "保存成功", Toast.LENGTH_SHORT).show();
                            DBinfo.getInstance(Xiugai.this).updateUserInfo("signature",
                                    etContent, name);
                            finish();
                        } else {
                            Toast.makeText(Xiugai.this, "签名不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        if (!TextUtils.isEmpty(etContent)) {
                           dBsqlite.getInstance(Xiugai.this). update(etContent);
                            finish();
                        } else {
                            Toast.makeText(Xiugai.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }

        }
    }
}
