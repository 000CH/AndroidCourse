package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindPass extends AppCompatActivity {
    private Button buttonuser,buttonfindback;
    private EditText edituser,editanswer;
    private TextView textView;
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    public String answertext="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);
        context=getApplicationContext();
        dbHelper = new DBHelper(context);
        buttonuser=findViewById(R.id.usernamecofirmFindPass);
        buttonfindback=findViewById(R.id.cofirmFindPass);
        edituser=findViewById(R.id.editusernameFindPass);
        editanswer=findViewById(R.id.editanswerFindPass);

        //显示密保问题
        textView=findViewById(R.id.textView12);
        buttonuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showquesiton();
            }
        });

        buttonfindback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findpass();
            }
        });
    }

    private void findpass() {
        /*
         * 当用户回答完问题且答对了，
         * 会将密码设为123456，
         * 然后挑战到程序界面*/
        if(editanswer.getText().toString().equals(answertext)&&(!answertext.isEmpty())){

            db=dbHelper.getWritableDatabase();
            db.execSQL("UPDATE user SET pwd = ? WHERE name = ?",
                    new String[]{"123456",edituser.getText().toString()});
            //写跳转函数
            db.close();
            Toast.makeText(context,"密码已设置为123456",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"出错了，请检查用户名和密保问题",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private void showquesiton() {
        db=dbHelper.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * FROM user where name=? ", new String[]{edituser.getText().toString()});
        if(cursor.moveToFirst()) {
            //判断是否查到了这个用户
            textView.setText(cursor.getString(cursor.getColumnIndex("question")));
            ;answertext=cursor.getString(cursor.getColumnIndex("answer"));
            System.out.println(answertext);
            cursor.close();
        }
        else{
            cursor.close();

            Toast.makeText(context,"用户名未找到",Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}