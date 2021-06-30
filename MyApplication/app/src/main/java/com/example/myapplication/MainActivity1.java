package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener{

    private CalendarView calendarView;
    private EditText scheduleInput;
    private Context context;
    private Button addSchedule,checkAdd;
    private String dateToday;//用于记录今天的日期

    private SQLiteDatabase myDatabase;
    private TextView mySchedule[] = new TextView[5];
    private int userID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        initView();
        Intent intent = getIntent();
        userID = intent.getIntExtra("userID",0);
        //这里不这样的话一进去就设置当天的日程会报错
        Calendar time = Calendar.getInstance();
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH)+1;//注意要+1，0表示1月份
        int day = time.get(Calendar.DAY_OF_MONTH);
        dateToday = year+"-"+month+"-"+day;


        //Toast.makeText(context, userID, Toast.LENGTH_SHORT).show();

        //Toast.makeText(MainActivity1.this,userID,Toast.LENGTH_LONG).show();
        //还要直接查询当天的日程，这个要放在initView的后面，不然会出问题
        queryByDate(dateToday);
        Button back = findViewById(R.id.buttonback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {


        context = this;
        addSchedule = findViewById(R.id.addSchedule);
        addSchedule.setOnClickListener(this);
        checkAdd = findViewById(R.id.checkAdd);
        checkAdd.setOnClickListener(this);

        calendarView = findViewById(R.id.calendar);
        scheduleInput = findViewById(R.id.scheduleDetailInput);

        calendarView.setOnDateChangeListener(mySelectDate);

        mySchedule[0] = findViewById(R.id.schedule1);
        mySchedule[1] = findViewById(R.id.schedule2);
        mySchedule[2] = findViewById(R.id.schedule3);
        mySchedule[3] = findViewById(R.id.schedule4);
        mySchedule[4] = findViewById(R.id.schedule5);/*主页面每一天日期下最多展示五个计划*/
        for(TextView v:mySchedule){
            v.setOnClickListener(this);
        }
    }

    private CalendarView.OnDateChangeListener mySelectDate = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
            dateToday = year+"-"+(month+1)+"-"+dayOfMonth;
            //Toast.makeText(context, "你选择了:"+dateToday, Toast.LENGTH_SHORT).show();
            //得把用别的日期查出来的日程删除并将其隐藏
            for(TextView v:mySchedule){
                v.setText("");
                v.setVisibility(View.GONE);
            }
            queryByDate(dateToday);
        }
    };

    //根据日期查询日程
    private void queryByDate(String date) {
        //columns为null 查询所有列
        DBHelper dbHelper = new DBHelper(MainActivity1.this);
        ArrayList<String> ss = dbHelper.getCalender(userID,date);

        int i=0;
        System.out.println("ccccccc??????"+ss.size());
        for(;i<ss.size();i++){
            mySchedule[i].setText("日程"+(i+1)+"："+ss.get(i));
            mySchedule[i].setVisibility(View.VISIBLE);
            if(i >= 5)
                break;
        }

        System.out.println("test?????"+userID);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addSchedule:
                addMySchedule();

                break;
            case R.id.checkAdd:
                checkAddSchedule();
                break;
            case R.id.schedule1:case R.id.schedule2:case R.id.schedule3:case R.id.schedule4:case R.id.schedule5:
                editSchedule(v);
                break;
        }
    }

    private void editSchedule(View v) {
        Intent intent = new Intent(MainActivity1.this, EditScheduleActivity.class);

        String sch = ((TextView) v).getText().toString().split("：")[1];
        if(!sch.equals("")){
            intent.putExtra("schedule",sch);
            intent.putExtra("userID",userID);
            intent.putExtra("time",dateToday);
            //System.out.println("changhong?????"+userID);
            startActivity(intent);


        }

    }

    private void checkAddSchedule() {
        ContentValues values = new ContentValues();
        String a = scheduleInput.getText().toString();
        if(!a.equals("")){
            //第一个参数是表中的列名
            values.put("userID",userID);
            values.put("scheduleDetail",a);
            values.put("time",dateToday);
            DBHelper dbHelper = new DBHelper(MainActivity1.this);
            dbHelper.insert("schedules",values);
//        myDatabase.insert("schedules",null,values);
            scheduleInput.setVisibility(View.GONE);
            checkAdd.setVisibility(View.GONE);
            calendarView.setVisibility(View.VISIBLE);
            queryByDate(dateToday);
            //添加完以后把scheduleInput中的内容清除
            scheduleInput.setText("");
            //System.out.println("changhong?????"+userID);
            //Toast.makeText(context, userID, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), "计划不能为空！", Toast.LENGTH_SHORT).show();}

    }

    private void addMySchedule() {
        scheduleInput.setVisibility(View.VISIBLE);
        checkAdd.setVisibility(View.VISIBLE);
        calendarView.setVisibility(View.GONE);
    }
}
