package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ac_classify extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify);
        Button b1 = findViewById(R.id.computer);
        Button b2 = findViewById(R.id.English);
        Button b3 = findViewById(R.id.math);
        Button b4 = findViewById(R.id.heart);
        Button b5 = findViewById(R.id.pe);
        Button b6 = findViewById(R.id.legal);
        Button b7 = findViewById(R.id.edu);
        Button b8 = findViewById(R.id.agr);
        RecyclerView recyclerView = findViewById(R.id.lis2);
        DBHelper dbHelper = new DBHelper(ac_classify.this);
        ArrayList<Video> videoArrayList = dbHelper.classification("计算机");
        // 线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 用于描述item的适配器
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b1.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b2.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b3.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b4.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b5.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
            b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b6.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
            b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b7.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
            b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = b8.getText().toString();
                DBHelper dbHelper = new DBHelper(ac_classify.this);
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_classify.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_classify.this,videoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
}
    public void comeback(View v){
        finish();
    }
}
