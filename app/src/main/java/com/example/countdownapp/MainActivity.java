package com.example.countdownapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Event;
import db.MySqliteOpenHelper;

public class MainActivity extends AppCompatActivity {

    //        主页面
    private ImageView me_btn;
    private ImageView add_btn;
    private ImageView day_plan_btn;
    private ListView ls_event;

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    private List<Event> all_ls_event = new ArrayList<>();
    private List<Map<String, Object>> map_ls_event = new ArrayList<>();
    private SimpleAdapter simpleAdapter;

    private int day = 0;
    private String now_time;
    private int interval_time;

    private String new_name;
    private String new_to_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        实例化数据库
        helper = MySqliteOpenHelper.getmInstance(this);
        db = helper.getReadableDatabase();

//        获取控件
        ls_event = findViewById(R.id.ls_event);
        me_btn = findViewById(R.id.me_btn);
        add_btn = findViewById(R.id.add_btn);
        day_plan_btn = findViewById(R.id.day_plan_btn);

//        判断是否添加
        judge_insert();

//        获取所有数据
        all_event();

        String[] from = {"name", "interval_time"};
        int[] to = {R.id.item_name, R.id.item_interval_time};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, map_ls_event, R.layout.item, from, to);

        ls_event.setAdapter(simpleAdapter);

//        跳转详情页面
        ls_event.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转详情页面
                Map<String, Object> map = map_ls_event.get(i);
                Intent to_count = new Intent(MainActivity.this, count_down_activity.class);
                to_count.putExtra("id",all_ls_event.get(i).getId());
                to_count.putExtra("name", map_ls_event.get(i).get("name").toString());
                to_count.putExtra("interval_time", map_ls_event.get(i).get("interval_time").toString());
                startActivity(to_count);
            }
        });
//        跳转添加页面
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, add_activity.class));
            }
        });
//        跳转day页面
        day_plan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,day_activity.class));
            }
        });
//        页面刷新
        me_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 页面刷新
//                清空原有的数据
                all_ls_event.clear();
                map_ls_event.clear();

                all_event();

                simpleAdapter.notifyDataSetChanged();
            }
        });
    }

    //    配置 listView
    private void initList() {

    }

    //    获取所有event
    private void all_event() {
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from events", null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String to_time = cursor.getString(2);
                Event event = new Event(id, name, to_time);
                all_ls_event.add(event);
                try {
                    havaDays(to_time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Map<String, Object> m = new HashMap<>();
                m.put("name", name);
                m.put("interval_time", interval_time + "天");
                map_ls_event.add(m);
            }
            cursor.close();
        }
    }

    //    添加倒计时
    private void insert_event() {
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put("name", new_name);
            values.put("to_time", new_to_time);
            db.insert("events", null, values);
        }
    }

    //    判断是否需要添加
    private void judge_insert() {
        Intent intent = getIntent();
        new_name = intent.getStringExtra("name");
        new_to_time = intent.getStringExtra("to_time");
        if (!(new_name == null)) {
            insert_event();
        }
    }

    //        计算倒计时
//    SimpleDateFormat
    public void havaDays(String to_time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        long from = date.getTime();
        Date toDate = simpleDateFormat.parse(to_time);
        long to = toDate.getTime();
        interval_time = (int) ((to - from) / (1000 * 60 * 60 * 24))+1;
        if(interval_time<0){
            interval_time = 0;
        }
    }
}