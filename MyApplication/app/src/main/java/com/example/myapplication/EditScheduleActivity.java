package com.example.myapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private String schedule;
    private Button editBtn, deleteBtn;
    private EditText scheduleInput;
    //private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase myDatabase;
    private int userID;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        // 首先获取到意图对象
        Intent intent = getIntent();
        // 获取到传递过来的姓名
        schedule = intent.getStringExtra("schedule");
        userID = intent.getIntExtra("userID", 0);
        time = intent.getStringExtra("time");
        initView();
    }

    private void initView() {
//        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
//        myDatabase = mySQLiteOpenHelper.getWritableDatabase();

        editBtn = findViewById(R.id.editBtn);
        editBtn.setOnClickListener(this);
        deleteBtn = findViewById(R.id.deleteSchedule);
        deleteBtn.setOnClickListener(this);
        scheduleInput = findViewById(R.id.scheduleInput);
        scheduleInput.setText(schedule);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteSchedule:
                deleteMySchedule();
                break;
            case R.id.editBtn:
                editSchedule();
                break;
        }
    }

    private void editSchedule() {
        DBHelper dbHelper = new DBHelper(EditScheduleActivity.this);
        if (!(scheduleInput.getText().toString().equals(""))) {

            dbHelper.UpdateCalender(userID, time, schedule, scheduleInput.getText().toString());
            Intent intent = new Intent(EditScheduleActivity.this, MainActivity1.class);
            intent.putExtra("userID",userID);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getBaseContext(), "计划不能为空！", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMySchedule() {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(EditScheduleActivity.this);
        adBuilder.setMessage("确认要删除计划吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper dbHelper = new DBHelper(EditScheduleActivity.this);
                dbHelper.deleteCalender(userID, time, schedule);
                Intent intent = new Intent(EditScheduleActivity.this, MainActivity1.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "删除计划成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = adBuilder.create();
        alertDialog.show();
    }
}

