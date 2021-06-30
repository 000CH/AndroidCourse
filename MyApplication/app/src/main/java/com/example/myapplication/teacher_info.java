package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class teacher_info extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);
        ImageView teacherImage = findViewById(R.id.image_teacher);
        TextView tt = findViewById(R.id.text_introduction);
        //teacherImage.setBackgroundResource(R.drawable.teacher01);
        DBHelper dbHelper = new DBHelper(teacher_info.this);
        Intent intent=getIntent();
        int userID=intent.getIntExtra("teacherID",0);
        String teachername = intent.getStringExtra("teachername");
        if(teachername.equals("罗翔")){
            teacherImage.setBackgroundResource(R.drawable.teacher01);
        }
        if(teachername.equals("张宇")){
            teacherImage.setBackgroundResource(R.drawable.teacher02);
        }
        if(teachername.equals("汤家凤")){
            teacherImage.setBackgroundResource(R.drawable.teacher03);
        }
        String text = teachername +"老师教授的课程";
        tt.setText(text);
        if(userID!=0){
            ArrayList<Video> classList = dbHelper.getTeacherclass(userID);
            RecyclerView recyclerView = findViewById(R.id.teacherCourses);
            // 线性布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(teacher_info.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            // 用于描述item的适配器
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(teacher_info.this,classList);
            recyclerView.setAdapter(recyclerAdapter);
        }
        ImageView im = findViewById(R.id.back22);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}