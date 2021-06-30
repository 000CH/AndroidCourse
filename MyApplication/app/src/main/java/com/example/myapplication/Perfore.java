package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Perfore extends AppCompatActivity {
    private EditText editname,editpass;
    private DBHelper dbHelper;
    private Context context;
    private Button buttonrigster,buttonlogin,buttonfindback;
    private SQLiteDatabase db1_user_get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfore);
        context=getApplicationContext();
        dbHelper=new DBHelper(context);
        editname=findViewById(R.id.editTextTextPersonName);
        editpass=findViewById(R.id.editTextTextPassword);
        buttonrigster=findViewById(R.id.buttonregister);
        buttonlogin=findViewById(R.id.buttonlogin);
        buttonfindback=findViewById(R.id.buttonfindbackpassword);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });
        buttonrigster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userregister();
            }
        });
        buttonfindback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userbuttonfindback();
            }
        });
    }

    private void userbuttonfindback() {
        finish();
        startActivity(new Intent(context,FindPass.class));
    }

    private void userlogin() {
        db1_user_get=dbHelper.getReadableDatabase();
        String textname=editname.getText().toString();
        String textpass=editpass.getText().toString();
        Cursor cursor=db1_user_get.rawQuery("select * FROM user where name=? ", new String[]{textname});
        if(cursor.moveToFirst()){
            //判断是否查到了这个用户
            int position=cursor.getColumnIndex("pwd");
            String passsource=cursor.getString(position);
            if (passsource.equals(textpass)){
                //挑跳转页面
                Intent intent = new Intent(Perfore.this, MainActivity.class);
                startActivity(intent);
                //
                cursor.close();
                Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                //finish();
                //
            }
            else{
                cursor.close();
                textpass="";
                Toast.makeText(context,"请输入正确密码!",Toast.LENGTH_SHORT).show();
            }
        }
        else{cursor.close();
            Toast.makeText(context,"用户名未注册，可以使用其注册",Toast.LENGTH_SHORT).show();}
        cursor.close();
    }

    private void userregister() {
        finish();
        startActivity(new Intent(context,Register.class));
    }
}