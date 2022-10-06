package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.ArrayList;
import java.util.List;
public class Photo extends AppCompatActivity {
    Button btn_upload;
    Button fh;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        btn_upload = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView2);
        fh=findViewById(R.id.button2);
        btn_upload.setOnClickListener(new myClick());
        fh.setOnClickListener(new Click());
    }
       class myClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent pickIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(pickIntent, 22);//打开相册是22
        }

    }
    class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {

          finish();
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22) {
            Toast toast = Toast.makeText(Photo.this,"图片src"+data.getDataString(),
                    Toast.LENGTH_LONG);
            toast.show();
            imageView.setImageURI(data.getData());
        }
    }
}
