package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {
    Button sczp,xgzl,cxcj,tcdl;
    TextView zh;
    TextView nc;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment2() {
        // Required empty public constructor

    }

    class myclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tcdl:
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.xgzl:
                    Bundle bundle=new Bundle();
                    bundle.putString("username",Yonghu.name);
                    Intent intent2 = new Intent(getActivity(), Gerenziliao.class);
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                    break;
                case R.id.sczp:
                    Intent intent3 = new Intent(getActivity(), Photo.class);
                    startActivity(intent3);
                    break;
                case R.id.cxcj:
                    Bundle bundle1=new Bundle();
                    bundle1.putString("username",Yonghu.name);
                    Intent intent4 = new Intent(getActivity(), Examview.class);
                    intent4.putExtras(bundle1);
                    startActivity(intent4);
                    break;
            }
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
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
        View view=inflater.inflate(R.layout.fragment_blank2, container, false);
        // Inflate the layout for this fragment
        sczp = view.findViewById(R.id.sczp);
        xgzl = view.findViewById(R.id.xgzl);
        cxcj = view.findViewById(R.id.cxcj);
        tcdl=  view.findViewById(R.id.tcdl);
        zh=  view.findViewById(R.id.t_zh);
        zh.setText(Yonghu.name);

        sczp.setOnClickListener(new myclick());
        xgzl.setOnClickListener(new myclick());
        cxcj.setOnClickListener(new myclick());
        tcdl.setOnClickListener(new myclick());

        return view;
    }
}