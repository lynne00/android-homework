package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Vedioplayer extends AppCompatActivity {
    Button btnStart,btnStop,btnPause;
    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedioplayer);
        btnStart=(Button)findViewById(R.id.bf);
        btnPause=findViewById(R.id.zt);
        btnStop=(Button)findViewById(R.id.tz);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder=surfaceView.getHolder();
        btnStart.setOnClickListener(new myClick());
        btnStop.setOnClickListener(new myClick());
        btnPause.setOnClickListener(new myClick());
        mediaPlayer =new MediaPlayer();


    }
    class myClick implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if (v==btnStart)
            {
                try {
                    String path="/sdcard/Movies/chengxin.mp4";
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.setDisplay(surfaceHolder);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (v==btnPause)
            {

                 if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    btnPause.setText("播放");
                }
                else
                {
                    mediaPlayer.start();
                    btnPause.setText("暂停");
                }

            }

            if(v==btnStop)
            {
                if (mediaPlayer.isPlaying())
                {
                mediaPlayer.stop();
                mediaPlayer.release();
                }
                finish();

            }
        }
    }


}
