package com.example.countdownapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class day_activity extends AppCompatActivity {

    private TextView day_back;
    private TextView day_mor_cek;
    private TextView day_aft_cek;
    private TextView day_eve_cek;
    private TextView day_pre_btn;
    private TextView day_0_btn;
    private EditText day_mor;
    private EditText day_aft;
    private EditText day_eve;

    private String day_mor_Str;
    private int day_mor_sta = 0;
    private String day_aft_Str;
    private int day_aft_sta = 0;
    private String day_eve_Str;
    private int day_eve_sta = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        day_back = findViewById(R.id.day_back);
        day_mor = findViewById(R.id.day_mor);
        day_mor_cek = findViewById(R.id.day_mor_cek);
        day_aft = findViewById(R.id.day_aft);
        day_aft_cek = findViewById(R.id.day_aft_cek);
        day_eve = findViewById(R.id.day_eve);
        day_eve_cek = findViewById(R.id.day_eve_cek);
        day_pre_btn = findViewById(R.id.day_pre_btn);
        day_0_btn = findViewById(R.id.day_0_btn);

        SharedPreferences day_plan = getSharedPreferences("day_plan", MODE_PRIVATE);

//        数据初始化
        day_mor_Str = day_plan.getString("mor_Str", "无");
        day_mor.setText(day_mor_Str);
        day_mor_sta = day_plan.getInt("day_mor_sta", 0);

        day_aft_Str = day_plan.getString("aft_Str", "无");
        day_aft.setText(day_aft_Str);
        day_aft_sta = day_plan.getInt("day_aft_sta", 0);

        day_eve_Str = day_plan.getString("eve_Str", "无");
        day_eve.setText(day_eve_Str);
        day_eve_sta = day_plan.getInt("day_eve_sta", 0);

        //        保存 数据
        day_pre_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mor_str = day_mor.getText().toString();
                String aft_str = day_aft.getText().toString();
                String eve_str = day_eve.getText().toString();
                SharedPreferences.Editor edit = day_plan.edit();
                edit.putString("mor_Str", mor_str);
                edit.putInt("day_mor_sta",day_mor_sta);
                edit.putString("aft_Str", aft_str);
                edit.putInt("day_aft_sta", day_aft_sta);
                edit.putString("eve_Str", eve_str);
                edit.putInt("day_eve_sta", day_eve_sta);
                edit.commit();
                Toast.makeText(day_activity.this,"保存计划成功！",Toast.LENGTH_LONG).show();
            }
        });

//        初始化 sta图片
        if (day_mor_sta == 0) {
            day_mor_cek.setBackgroundResource(R.drawable.day_none);
        } else {
            day_mor_cek.setBackgroundResource(R.drawable.day_ok);
        }
        if (day_aft_sta == 0) {
            day_aft_cek.setBackgroundResource(R.drawable.day_none);
        } else {
            day_aft_cek.setBackgroundResource(R.drawable.day_ok);
        }
        if (day_eve_sta == 0) {
            day_eve_cek.setBackgroundResource(R.drawable.day_none);
        } else {
            day_eve_cek.setBackgroundResource(R.drawable.day_ok);
        }

//        点击 状态
        day_mor_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (day_mor_sta == 1) {
                    day_mor_sta = 0;
                    day_mor_cek.setBackgroundResource(R.drawable.day_none);
                } else {
                    day_mor_sta = 1;
                    day_mor_cek.setBackgroundResource(R.drawable.day_ok);
                }
            }
        });
        day_aft_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (day_aft_sta == 1) {
                    day_aft_sta = 0;
                    day_aft_cek.setBackgroundResource(R.drawable.day_none);
                } else {
                    day_aft_sta = 1;
                    day_aft_cek.setBackgroundResource(R.drawable.day_ok);
                }
            }
        });
        day_eve_cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (day_eve_sta == 1) {
                    day_eve_sta = 0;
                    day_eve_cek.setBackgroundResource(R.drawable.day_none);
                } else {
                    day_eve_sta = 1;
                    day_eve_cek.setBackgroundResource(R.drawable.day_ok);
                }
            }
        });

//        重置 内容
        day_0_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day_mor.setText("");
                day_aft.setText("");
                day_eve.setText("");
            }
        });

//        跳转回首页
        day_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(day_activity.this, MainActivity.class));
            }
        });
    }

}