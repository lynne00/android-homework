package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Examview extends AppCompatActivity {
    ListView lv;
    Button fh;
    Userexam userexam = null;
    String name;

    public SimpleAdapter adapter;
    public ArrayList<Map<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.examview);
        Bundle bundle=this.getIntent().getExtras();
        name=bundle.getString("username");
        userexam = DBexam.getInstance(this).getUserExam(name);

        if (userexam == null) {
            userexam = new Userexam();
            userexam.userName = name;//zh
            userexam.androidexam = -1;
            userexam.javaexam = -1;
            DBexam.getInstance(this).saveUserExam(userexam);
        }
        lv = findViewById(R.id.lv);
        fh = findViewById(R.id.fh);

        data = getData();
        adapter = new SimpleAdapter(Examview.this, data, R.layout.item,
                new String[]{"name", "et_name"}, new int[]{R.id.textView,R.id.textView2});
        fh.setOnClickListener(new Examview.myClick());
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
    class myClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == fh) {
                finish();
            }

        }
    }
    public ArrayList<Map<String,Object>> getData() {
        ArrayList<Map<String,Object>> d = new ArrayList<Map<String,Object>>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "Java成绩");
        map1.put("et_name",userexam.javaexam );
        Map<String, Object>map2 = new HashMap<>();
        map2.put("name", "android成绩");
        map2.put("et_name",userexam.androidexam );
        d.add(map1);
        d.add(map2);
        return d;
    }
}