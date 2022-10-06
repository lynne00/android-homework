package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    ListView lv;
    Button play;
    TextView time;
    Timer timer;
    Calendar mCalendar;
    public SimpleAdapter adapter;
    public ArrayList<Map<String, Object>> data;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        timer = new Timer();
        time=view.findViewById(R.id.time);
        lv = view.findViewById(R.id.lv1);
        play=view.findViewById(R.id.vedio);
        play.setOnClickListener(new myclick());
        // Inflate the layout for this fragment
        data = getData();
        adapter = new SimpleAdapter(getActivity(), data, R.layout.item1,
                new String[]{"st"}, new int[]{R.id.st});
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setOnItemClickListener(new lvClick());

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.v("Timer","run()...");
                mCalendar = Calendar.getInstance();
                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);//HOUR    进制为12小时   HOUR_OF_DAY  为24小时
                int minute = mCalendar.get(Calendar.MINUTE);//分钟
                int second = mCalendar.get(Calendar.SECOND) + 1;//秒数
                if (second == 60) {
                    minute += 1;
                    second = 0;
                }
                if (minute == 60){
                    hour += 1;
                    minute = 0;
                }
                if (hour == 12){
                    hour = 0;
                }
                String time = String.format("%d:%02d:%02d", hour, minute, second);
                mCalendar.set(Calendar.SECOND, second);
                mCalendar.set(Calendar.MINUTE, minute);
                mCalendar.set(Calendar.HOUR_OF_DAY, hour);

                Message message=new Message();
                message.what=0;
                message.obj=time;
                mHandler.sendMessage(message);

            }
        },0,1000);
        return view;
    }
    class lvClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            if(i==0)
            {
                Intent intent1 = new Intent(getActivity(),TestJava.class);
                startActivity(intent1);
            }
            if(i==1)
            {
                Intent intent1 = new Intent(getActivity(),TestAndroid.class);
                startActivity(intent1);
            }
            if(i==2)
            {
                Toast.makeText(getActivity(), "待开发", Toast.LENGTH_SHORT).show();
            }
            if(i==3)
            {
                Toast.makeText(getActivity(), "待开发", Toast.LENGTH_SHORT).show();
            }
            if(i==4)
            {
                Toast.makeText(getActivity(), "待开发", Toast.LENGTH_SHORT).show();
            }
        }
    }
    class myclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.vedio:
                    Intent intent = new Intent(getActivity(),Vedioplayer.class);
                    startActivity(intent);
                    break;

            }
        }
    }

    public ArrayList<Map<String,Object>> getData() {
        ArrayList<Map<String,Object>> d = new ArrayList<Map<String,Object>>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("st", "java测试题");
        Map<String, Object>map2 = new HashMap<>();
        map2.put("st", "android测试题");
        Map<String, Object>map3 = new HashMap<>();
        map3.put("st", "待开发");
        Map<String, Object>map4 = new HashMap<>();
        map4.put("st", "待开发");
        Map<String, Object>map5 = new HashMap<>();
        map5.put("st", "待开发");
        d.add(map1);
        d.add(map2);
        d.add(map3);
        d.add(map4);
        d.add(map5);
        return d;
    }
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.v("Timer","handleMessage()..");
            super.handleMessage(msg);
            String str=(String)msg.obj;
           time.setText(str);
        }
    };



}