package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.os.Handler;
import java.util.ArrayList;

public class ac_serach extends AppCompatActivity {

    private SearchView searchView;
    private Button btn;
    private ArrayList<String> mdate=new ArrayList<String>();
    private Handler handler = new Handler();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        init();
    }

    private void init() {
        btn=findViewById(R.id.cancel_button);
        searchView=findViewById(R.id.search);
        mdate.add("计算机");
        mdate.add("英语");
        mdate.add("工学");
        mdate.add("心理学");
        listView = findViewById(R.id.list22);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mdate));
        listView.setTextFilterEnabled(true);//打开过滤
        listView.setVisibility(View.GONE);
        searchView.onActionViewExpanded();//焦点
        //searchView.clearFocus();//clear焦点
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {//添加搜索框监听4
            @Override
            public boolean onQueryTextSubmit(String s) {
                System.out.println(s);
                searchCourse(s);
                listView.setVisibility(View.GONE);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {

                if(!TextUtils.isEmpty(s)){
                    listView.setVisibility(View.VISIBLE);//设置当搜索框不为空的时候显示搜索数据
                    listView.setFilterText(s);//添加字符过滤
                }else {
                    listView.setVisibility(View.GONE);//设置当搜索框为空的时候隐藏LISTVIEW
                    listView.clearTextFilter();//删除字符过滤
                }
                return false;
            }

        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void searchCourse(String text){
        DBHelper dbHelper = new DBHelper(ac_serach.this);
        ArrayList<Video> searchList = dbHelper.searchByname(text);
        ArrayList<Video> searchListBykind = dbHelper.classification(text);
        searchList.addAll(searchListBykind);
        RecyclerView recyclerView = findViewById(R.id.searchCourses);
        // 线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ac_serach.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 用于描述item的适配器
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ac_serach.this,searchList);
        recyclerView.setAdapter(recyclerAdapter);
    }

}
