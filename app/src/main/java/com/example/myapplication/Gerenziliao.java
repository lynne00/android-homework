package com.example.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gerenziliao extends AppCompatActivity {
    public static String name;
    ListView lv;
    Useinfo useinfo = null;
    Button fh;
    ImageView imageView;

    public SimpleAdapter adapter;
    public ArrayList<Map<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenziliao);
        Bundle bundle=this.getIntent().getExtras();
        name=bundle.getString("username");
        useinfo = DBinfo.getInstance(this).getUserInfo(name);


        //如果第一次进入，数据库没有保留用户信息
        if (useinfo == null) {
            useinfo = new Useinfo();
            useinfo.userName = name;//zh
            useinfo.nickName = "";
            useinfo.sex = "";
            useinfo.signature = "这个人很懒，什么都没留下...";
            DBinfo.getInstance(this).saveUserInfo(useinfo);
        }
        setContentView(R.layout.gerenziliao);
        lv = findViewById(R.id.lv);
        fh = findViewById(R.id.fh);
        imageView=findViewById(R.id.tx);
        data = getData();
        adapter = new SimpleAdapter(Gerenziliao.this, data, R.layout.item,
                new String[]{"name", "et_name"}, new int[]{R.id.textView,R.id.textView2});
        fh.setOnClickListener(new Gerenziliao.myClick());
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setOnItemClickListener(new lvClick());
        imageView.setOnClickListener(new myClick1());
    }
    class myClick1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent pickIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(pickIntent, 22);//打开相册是22
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22) {
            Toast toast = Toast.makeText(Gerenziliao.this,"图片src"+data.getDataString(),
                    Toast.LENGTH_LONG);
            toast.show();
            imageView.setImageURI(data.getData());
        }
    }
    class myClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == fh) {
                setResult(1);
                finish();
            }

        }
    }
    class lvClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if(i==1)
            {
                Bundle bundle = new Bundle();
                bundle.putString("content", useinfo.nickName);
                bundle.putString("username",  name );
                bundle.putString("title", "修改昵称");
                bundle.putInt("flag", 1);
                Toast.makeText(Gerenziliao.this, "修改昵称", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Gerenziliao.this,Xiugai.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
                finish();
            }
            if(i==2)
            {
                Bundle bundle = new Bundle();
                bundle.putString("content",  useinfo.sex );
                bundle.putString("username",  name );
                bundle.putString("title", "修改性别");
                bundle.putInt("flag", 2);
                Toast.makeText(Gerenziliao.this, "修改性别", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Gerenziliao.this,Xiugai.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
                finish();
            }
            if(i==3)
            {
                Bundle bundle = new Bundle();
                bundle.putString("content",  useinfo.signature );
                bundle.putString("username",  name );
                bundle.putString("title", "修改个性签名");
                bundle.putInt("flag", 3);
                Toast.makeText(Gerenziliao.this, "修改个性签名", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Gerenziliao.this,Xiugai.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
                finish();
            }
            if(i==4)
            {
                Bundle bundle = new Bundle();
                bundle.putString("username", name);
                Toast.makeText(Gerenziliao.this, "修改密码", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Gerenziliao.this,Xiugaipassword.class);
                intent1.putExtras(bundle);
                startActivity(intent1);
                finish();
            }
        }
    }

    public ArrayList<Map<String,Object>> getData() {
        ArrayList<Map<String,Object>> d = new ArrayList<Map<String,Object>>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "账   号");
       map1.put("et_name",useinfo.userName );
        Map<String, Object>map2 = new HashMap<>();
        map2.put("name", "昵   称");
        map2.put("et_name",useinfo.nickName );
        Map<String, Object>map3 = new HashMap<>();
        map3.put("name", "性   别");
        map3.put("et_name",useinfo.sex );
        Map<String, Object>map4 = new HashMap<>();
        map4.put("name", "个性签名");
        map4.put("et_name",useinfo.signature );
        Map<String, Object>map5 = new HashMap<>();
        map5.put("name", "修改密码");
        d.add(map1);
        d.add(map2);
        d.add(map3);
        d.add(map4);
        d.add(map5);
        return d;
    }

}