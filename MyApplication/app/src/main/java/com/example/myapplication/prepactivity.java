package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class prepactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prep);
        Button button11=(Button)findViewById(R.id.button11);
        Button button27=(Button)findViewById(R.id.button27);
        EditText txt=(EditText) findViewById(R.id.editTextTextPersonName) ;
        DBOpenHelper dbsqLiteOpenHelper = new DBOpenHelper(prepactivity.this,"background.db",null,1);
        final SQLiteDatabase db = dbsqLiteOpenHelper.getWritableDatabase();


        Cursor cursor1 = db.query("background", new String[]{"id","ph"}, "id=?", new String[]{"1"}, null, null, null);
        //利用游标遍历所有数据对象
        while(cursor1.moveToNext()){
            String id = cursor1.getString(cursor1.getColumnIndex("id"));
            //String pn = cursor1.getString(cursor1.getColumnIndex("pn"));
            String ph = cursor1.getString(cursor1.getColumnIndex("ph"));


            Log.i("setactivity","result: id="  + id +" ph: " + ph  );
            if (ph.equals("1"))
                getWindow().setBackgroundDrawableResource(R.drawable.caihong);
            else if (ph.equals("2"))

                getWindow().setBackgroundDrawableResource(R.drawable.sky);
        }
        // 关闭游标，释放资源
        cursor1.close();
        button27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("链接: https://pan.baidu.com/s/1j7-eYiXzUGRUQU6xypuiZA 提取码: fkvk 复制这段内容后打开百度网盘手机App，操作更方便哦");

            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}