package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class interest extends AppCompatActivity {

    ListView lst1;
    private ArrayList<String> myfavs;
    private List<Map<String,Object>> lists1;
    private  String classify[]={"计算机","英语","工学","心理学","体育","法学","教育","农林"};
    private int flag[]={0,0,0,0,0,0,0,0};
    private  int count=0;
    private Button submit;
    private  int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_interest);
        lst1=findViewById(R.id.lis1);
        submit=findViewById(R.id.button2);
        Button cancle =findViewById(R.id.button3);
        myfavs = new ArrayList<String>();
        lists1 = new ArrayList<>();
        for(int i = 0;i < classify.length;i++){
            Map<String,Object> map =new HashMap<>();
            map.put("classify",classify[i]);
            lists1.add(map);
        }
        SimpleAdapter simpleAdapter =new SimpleAdapter(interest.this,lists1, R.layout.item_instest_check
                ,new String[]{"classify"}
                ,new int[]{R.id.category});
        lst1.setAdapter(simpleAdapter);
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  final CheckBox cb= view.findViewById(R.id.cbCheckBox);
                  {
                      if (cb.isChecked()) {
                          cb.setChecked(false);
                          flag[position] = 0;
                          count--;
                      } else {
                          if(count<4){
                              cb.setChecked(true);
                              flag[position] = 1;
                              count++;
                          }

                      }
                  }

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(interest.this);
                SQLiteDatabase sdb = dbHelper.getWritableDatabase();

                //添加新的订阅
                for(int i=0;i<8;i++)
                {
                    if(flag[i]==1)
                    {
                        myfavs.add(classify[i]);
                    }
                }
                if(myfavs.size()<4){

                    Toast.makeText(interest.this,"请选择4个兴趣！",Toast.LENGTH_SHORT).show();
                }
                else {

                    //删除所有订阅
                    dbHelper.deletefavorite(userId);
                    ContentValues contentValues = new ContentValues();
                    ContentValues contentValues1 = new ContentValues();
                    ContentValues contentValues2= new ContentValues();
                    ContentValues contentValues3 = new ContentValues();
                    contentValues.put("userID",userId);
                    contentValues.put("kind",myfavs.get(0));
                    dbHelper.insert("favorite",contentValues);

                    contentValues1.put("userID",userId);
                    contentValues1.put("kind",myfavs.get(1));
                    dbHelper.insert("favorite",contentValues1);

                    contentValues2.put("userID",userId);
                    contentValues2.put("kind",myfavs.get(2));
                    dbHelper.insert("favorite",contentValues2);

                    contentValues3.put("userID",userId);
                    contentValues3.put("kind",myfavs.get(3));
                    dbHelper.insert("favorite",contentValues3);


                    sdb.close();
                    dbHelper.close();
                    Intent intent =new Intent();
                    intent.setClass(interest.this,MainActivity.class);
                    intent.putExtra("userId",userId);
                    startActivity(intent);
                    finish();
                }



            }

        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }



}
