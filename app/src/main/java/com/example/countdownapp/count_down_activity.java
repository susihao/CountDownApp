package com.example.countdownapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SplittableRandom;

import db.MySqliteOpenHelper;


public class count_down_activity extends AppCompatActivity {

//    事件详情页面
    private TextView event_name;
    private TextView interval_time;
    private ImageView back_btn;
    private ImageView del_btn;

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

//        获取 组件
        event_name = findViewById(R.id.event_name);
        interval_time = findViewById(R.id.interval_time);
        back_btn = findViewById(R.id.back_btn);
        del_btn = findViewById(R.id.del_btn);

        //        实例化数据库
        helper = MySqliteOpenHelper.getmInstance(this);
        db = helper.getReadableDatabase();

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",100);
        String name = intent.getStringExtra("name");
        String interval = intent.getStringExtra("interval_time");
        interval = interval.substring(0,interval.length()-1);

//        配置数据
        event_name.setText(name);
        interval_time.setText(interval);

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deteleEvent(id);
                startActivity(new Intent(count_down_activity.this,MainActivity.class));
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(count_down_activity.this,MainActivity.class));
            }
        });

    }
//    删除数据
    private void deteleEvent(int id){
        db.delete("events","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

}