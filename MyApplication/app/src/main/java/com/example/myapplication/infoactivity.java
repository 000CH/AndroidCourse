package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class infoactivity extends AppCompatActivity {
    private String Sex;
    private int userId;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        userId=getIntent().getIntExtra("userID",0);
        Toast.makeText(this,Integer.toString(userId),Toast.LENGTH_SHORT).show();
        DBHelper dbHelper = new DBHelper(infoactivity.this);
        Person person=(Person) dbHelper.getUserinfo(userId);




        EditText edt1=(EditText)findViewById(R.id.edt1) ;//获取姓名1
        EditText edt2=(EditText)findViewById(R.id.edt2) ;//获取昵称1
        EditText edt3=(EditText)findViewById(R.id.edt3) ;//获取年龄1
        EditText edt4=(EditText)findViewById(R.id.edt4) ;//获取密码
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radio);//显示性别1
        TextView txt2=(TextView)findViewById(R.id.id2);//显示id
        txt2.setText("用户id："+Integer.toString(userId));

        Button button5=(Button)findViewById(R.id.button5); //修改头像
        Button button4=(Button)findViewById(R.id.button4);//返回上一级
        Button button6=(Button)findViewById(R.id.button6) ;//提交信息
//        ConstraintLayout constraintLayout2=findViewById(R.id.acticity2);//设置个人信息界面背景
//        constraintLayout2.setBackgroundResource(R.drawable.caihong);

        edt1.setText(person.getName());
        edt2.setText(person.getNickname());
        edt3.setText(person.getAge());
        for (int i=0;i<radioGroup.getChildCount();i++) {
            RadioButton r=(RadioButton)radioGroup.getChildAt(i);
            if (r.getText().toString().equals(person.getSex())){
                r.setChecked(true);
            }
        }
        Toast.makeText(infoactivity.this,person.getName(),Toast.LENGTH_SHORT).show();



        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               finish();
            }
        });
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name =edt1.getText().toString();
                String nickname=edt2.getText().toString();
                String age=edt3.getText().toString();
                //Toast.makeText(infoactivity.this,name+nickname+age+Sex,Toast.LENGTH_SHORT).show();
                for (int i=0;i<radioGroup.getChildCount();i++) {
                    RadioButton r=(RadioButton)radioGroup.getChildAt(i);
                    if (r.isChecked()){
                         Sex=r.getText().toString();
                        break;
                    }
                }
                DBHelper dbHelper =new DBHelper(infoactivity.this);
                dbHelper.UpdateUserinfo(userId,name,nickname,"",age,Sex);
                Toast.makeText(infoactivity.this,"保存完成",Toast.LENGTH_SHORT).show();
                finish();



            }
        });

    }
}