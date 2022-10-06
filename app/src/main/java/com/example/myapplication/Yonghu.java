package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class Yonghu extends AppCompatActivity {
    public static String name;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    List<Fragment> fragmentList = new ArrayList<>();
    TabLayoutMediator m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yonghu);
        viewPager2=findViewById(R.id.vp);
        tabLayout=findViewById(R.id.tb);


        fragmentList.add(new BlankFragment());
        fragmentList.add(new BlankFragment2());
        Bundle bundle=this.getIntent().getExtras();
        name=bundle.getString("username");

        MypageAdapter mypageAdapter=new MypageAdapter(getSupportFragmentManager(),getLifecycle(),fragmentList);
        viewPager2.setAdapter(mypageAdapter);
        viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);
        m=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                int [] imgs =new int[]{R.drawable.img1,R.drawable.img2};

                LinearLayout linearLayout=initView(position,imgs);
                tab.setCustomView(linearLayout);
            }
        });
        m.attach();
        tabLayout.addOnTabSelectedListener(new t());
    }
    public LinearLayout initView(int position,int []imgs){
        String []titles=new String[]{"系统界面","个人中心"};
        ImageView imageView =new ImageView(Yonghu.this);

//             tab.setText(titles.get(position));
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(1000,50);
        imageView.setLayoutParams(params);
        imageView.setImageResource(imgs[position]);



        TextView textView =new TextView(Yonghu.this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(titles[position]);




        LinearLayout linearLayout=new LinearLayout(Yonghu.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(imageView,0);
        linearLayout.addView(textView,1);
        linearLayout.setGravity(Gravity.CENTER);
        return linearLayout;
    }
    class  t implements TabLayout.OnTabSelectedListener{

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            tab.setCustomView(null);
            int position=tab.getPosition();
            int [] imgs =new int[]{R.drawable.img1_1,R.drawable.img2};
            if (position==0)
            {
                imgs =new int[]{R.drawable.img1_1,R.drawable.img2};
            }
            else if(position==1)
            {
                imgs =new int[]{R.drawable.img1,R.drawable.img2_1};
            }
            LinearLayout linearLayout=initView(position,imgs);
            tab.setCustomView(linearLayout);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            tab.setCustomView(null);
            int position=tab.getPosition();
            int [] imgs =new int[]{R.drawable.img1_1,R.drawable.img2};

            if (position==0)
            {
                imgs =new int[]{R.drawable.img1,R.drawable.img2_1};

            }
            else if(position==1)
            {
                imgs =new int[]{R.drawable.img1_1,R.drawable.img2};
            }


            LinearLayout linearLayout=initView(position,imgs);
            tab.setCustomView(linearLayout);

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }
}