package com.example.countdownapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.countdownapp.R;

import db.MySqliteOpenHelper;

public class add_activity extends AppCompatActivity {


    private EditText event_name;
    private EditText event_to_time;
    private ImageView cancle_btn;
    private ImageView det_btn;
    private ImageView me_btn;
    private ImageView clear_btn;

    private MySqliteOpenHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        event_name = findViewById(R.id.event_name);
        event_to_time = findViewById(R.id.event_to_time);
        det_btn = findViewById(R.id.det_btn);
        cancle_btn = findViewById(R.id.cancle_btn);
        me_btn = findViewById(R.id.me_btn);
        clear_btn = findViewById(R.id.clear_btn);

//        添加
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event_name.setText("");
                event_to_time.setText("");
            }
        });
//        放回me页面
        me_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(add_activity.this,MainActivity.class));
            }
        });
//        确认添加
        det_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              添加 event
                String name = event_name.getText().toString();
                String to_time = event_to_time.getText().toString();

                if(name.equals("")){
                    Toast.makeText(add_activity.this,"倒计时名不能为空哦！",Toast.LENGTH_SHORT).show();
                }else if(to_time.equals("")){
                    Toast.makeText(add_activity.this,"最后时间不能为空哦！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(add_activity.this,"倒计时添加成功！",Toast.LENGTH_LONG).show();
                    Intent to_add = new Intent(add_activity.this, MainActivity.class);
                    to_add.putExtra("name",name);
                    to_add.putExtra("to_time",to_time);
                    startActivity(to_add);
                }
            }
        });
//        取消添加 返回Main页面
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(add_activity.this,MainActivity.class));
            }
        });
    }

}