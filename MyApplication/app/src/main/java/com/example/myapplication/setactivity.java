package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class setactivity extends AppCompatActivity {
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
        DBOpenHelper dbsqLiteOpenHelper = new DBOpenHelper(setactivity.this,"background.db",null,1);
        final SQLiteDatabase db = dbsqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put("pn","test");
        values.put("ph","1");

        //数据库执行插入命令
        db.insert("background", null, values);
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

        Button button13=(Button)findViewById(R.id.button13);

        /*ConstraintLayout constraintLayout6=findViewById(R.id.acticity6);//设置设置界面背景
        constraintLayout6.setBackgroundResource(R.drawable.caihong);*/
        Spinner spinner=(Spinner)findViewById(R.id.spinner);
        spinner.getSelectedItem();


        //SharedPreferences sp=getSharedPreferences("mrsoft",MODE_PRIVATE);
        //SharedPreferences.Editor editor=sp.edit();

        /**选项选择监听*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择列表项的操作
                String[] backgroud = getResources().getStringArray(R.array.background);
                result=backgroud[position];
                if (result.equals("caihong"))
                {
                    ContentValues values = new ContentValues();
                    //values.put("username",name);
                    values.put("ph","1");

                    //数据库执行插入命令
                    db.update("background", values, "id=?", new String[]{"1"});
                    //getWindow().setBackgroundDrawableResource(R.drawable.caihong);
                }
                else if (result.equals("sky")){
                    ContentValues values = new ContentValues();
                    //values.put("username",name);
                    values.put("ph","2");

                    //数据库执行插入命令
                    db.update("background", values, "id=?", new String[]{"1"});
                    //getWindow().setBackgroundDrawableResource(R.drawable.sky);
                    ;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }});




        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(setactivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}