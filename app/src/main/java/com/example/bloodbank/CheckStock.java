package com.example.bloodbank;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CheckStock extends Fragment {

    private View view;

    private TextView t11,t22,t33,t44,t55,t66,t77,t88,t1,t2,t3,t4,t5,t6,t7,t8;
    private ImageButton p1, p2, p3, p4, p5, p6, p7, p8, m1, m2, m3, m4, m5, m6, m7, m8;
    Switch switch1;
    Button sb,ab;

    public final static String SHARED_PREF = "shared";
    public final static String TEXT1 = "text1";
    public final static String TEXT2 = "text2";
    public final static String TEXT3 = "text3";
    public final static String TEXT4 = "text4";
    public final static String TEXT5 = "text5";
    public final static String TEXT6 = "text6";
    public final static String TEXT7 = "text7";
    public final static String TEXT8 = "text8";
    public final static String SWITCH1 = "switch1";
    private  String text1,text2,text3,text4,text5,text6,text7,text8;
    private Boolean switchOnOff;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_check_stock, container, false);
        return view;
    }

    public void saveData1()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT1,t1.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData1()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text1 = sharedPreferences.getString(TEXT1,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update1()
    {
        t1.setText(text1);
        switch1.setChecked(switchOnOff);
    }

    public void saveData2()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT2,t2.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData2()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text2 = sharedPreferences.getString(TEXT2,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update2()
    {
        t2.setText(text2);
        switch1.setChecked(switchOnOff);
    }

    public void saveData3()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT3,t3.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData3()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text3 = sharedPreferences.getString(TEXT3,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update3()
    {
        t3.setText(text3);
        switch1.setChecked(switchOnOff);
    }

    public void saveData4()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT4,t4.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData4()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text4 = sharedPreferences.getString(TEXT4,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update4()
    {
        t4.setText(text4);
        switch1.setChecked(switchOnOff);
    }

    public void saveData5()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT5,t5.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData5()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text5 = sharedPreferences.getString(TEXT5,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update5()
    {
        t5.setText(text5);
        switch1.setChecked(switchOnOff);
    }

    public void saveData6()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT6,t6.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData6()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text6 = sharedPreferences.getString(TEXT6,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update6()
    {
        t6.setText(text6);
        switch1.setChecked(switchOnOff);
    }

    public void saveData7()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT7,t7.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData7()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text7 = sharedPreferences.getString(TEXT7,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update7()
    {
        t7.setText(text7);
        switch1.setChecked(switchOnOff);
    }

    public void saveData8()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT8,t8.getText().toString());
        editor.putBoolean(SWITCH1,switch1.isChecked());
        editor.apply();
    }
    public void loadData8()
    {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        text8 = sharedPreferences.getString(TEXT8,"");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
    }
    public void update8()
    {
        t8.setText(text8);
        switch1.setChecked(switchOnOff);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int x = 50;
        t11 = view.findViewById(R.id.tV11);
        t11.setText(String.valueOf(x));
        t22 = view.findViewById(R.id.tV22);
        t22.setText(String.valueOf(x));
        t33 = view.findViewById(R.id.tV33);
        t33.setText(String.valueOf(x));
        t44 = view.findViewById(R.id.tV44);
        t44.setText(String.valueOf(x));
        t55 = view.findViewById(R.id.tV55);
        t55.setText(String.valueOf(x));
        t66 = view.findViewById(R.id.tV66);
        t66.setText(String.valueOf(x));
        t77 = view.findViewById(R.id.tV77);
        t77.setText(String.valueOf(x));
        t88 = view.findViewById(R.id.tV88);
        t88.setText(String.valueOf(x));
        t1= view.findViewById(R.id.tV1);
        t2= view.findViewById(R.id.tV2);
        t3= view.findViewById(R.id.tV3);
        t4= view.findViewById(R.id.tV4);
        t5= view.findViewById(R.id.tV5);
        t6= view.findViewById(R.id.tV6);
        t7= view.findViewById(R.id.tV7);
        t8= view.findViewById(R.id.tV8);

        p1 = view.findViewById(R.id.B1);
        p2 = view.findViewById(R.id.B2);
        p3 = view.findViewById(R.id.B3);
        p4 = view.findViewById(R.id.B4);
        p5 = view.findViewById(R.id.B5);
        p6 = view.findViewById(R.id.B6);
        p7 = view.findViewById(R.id.B7);
        p8 = view.findViewById(R.id.B8);
        m1 = view.findViewById(R.id.B11);
        m2 = view.findViewById(R.id.B22);
        m3 = view.findViewById(R.id.B33);
        m4 = view.findViewById(R.id.B44);
        m5 = view.findViewById(R.id.B55);
        m6 = view.findViewById(R.id.B66);
        m7 = view.findViewById(R.id.B77);
        m8 = view.findViewById(R.id.B88);
        ab = view.findViewById(R.id.ab);
        sb = view.findViewById(R.id.sb);
        switch1 = view.findViewById(R.id.switch1);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count;
                count = Integer.parseInt(t11.getText().toString());
                count++;

                t11.setText(String.valueOf(count));
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t22.getText().toString());
                i = i + 1;
                t22.setText(String.valueOf(i));
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t33.getText().toString());
                i = i + 1;
                t33.setText(String.valueOf(i));

            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t44.getText().toString());
                i = i + 1;
                t44.setText(String.valueOf(i));
            }
        });

        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t55.getText().toString());
                i = i + 1;
                t55.setText(String.valueOf(i));
            }
        });

        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t66.getText().toString());
                i = i + 1;
                t66.setText(String.valueOf(i));
            }
        });

        p7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t77.getText().toString());
                i = i + 1;
                t77.setText(String.valueOf(i));
            }
        });

        p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t88.getText().toString());
                i = i + 1;
                t88.setText(String.valueOf(i));
            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t11.getText().toString());
                i = i - 1;
                t11.setText(String.valueOf(i));

            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t22.getText().toString());
                i = i - 1;
                t22.setText(String.valueOf(i));
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t33.getText().toString());
                i = i - 1;
                t33.setText(String.valueOf(i));
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t44.getText().toString());
                i = i - 1;
                t44.setText(String.valueOf(i));
            }
        });

        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t55.getText().toString());
                i = i - 1;
                t55.setText(String.valueOf(i));
            }
        });

        m6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t66.getText().toString());
                i = i - 1;
                t66.setText(String.valueOf(i));
            }
        });

        m7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(t77.getText().toString());
                i = i - 1;
                t77.setText(String.valueOf(i));
            }
        });

        m8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(t88.getText().toString());
                i = i - 1;
                t88.setText(String.valueOf(i));
            }
        });

        ab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                t1.setText(t11.getText().toString());
                t2.setText(t22.getText().toString());
                t3.setText(t33.getText().toString());
                t4.setText(t44.getText().toString());
                t5.setText(t55.getText().toString());
                t6.setText(t66.getText().toString());
                t7.setText(t77.getText().toString());
                t8.setText(t88.getText().toString());
            }

        });


        sb.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                saveData1();
                saveData2();
                saveData3();
                saveData4();
                saveData5();
                saveData6();
                saveData7();
                saveData8();
            }

        });

        loadData1();
        update1();
        loadData2();
        update2();
        loadData3();
        update3();
        loadData4();
        update4();
        loadData5();
        update5();
        loadData6();
        update6();
        loadData7();
        update7();
        loadData8();
        update8();
    }
}