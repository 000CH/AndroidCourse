package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pageFragment_home extends Fragment {
    @Nullable

    private Button mbtn1;
    private Button mbtn2;
    private Button mbtn3;
    private Button mbtn4;
    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private EditText ed4;
    private TextView tv;
    private ImageButton ib1,ib2,ib_interest;
    private String name,category,sex,age;
    private SearchView searchView;
    private ImageButton classify;
    private LinearLayout ll_search;
    private int count=0;
    private ImageView im;
    private int MXABANNER=4;
    private Handler handler = new Handler();
    private ListView listView,lst;
    private RecyclerView lsc;
    private ArrayList<String> mdate;
    private int[] imagePath =new int[]{

            R.drawable.course_01,
            R.drawable.course_02,
            R.drawable.course_03,
            R.drawable.course_04,

    };
	private int userId;
	public pageFragment_home(int userId){
		this.userId = userId;
	}


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //要执行的方法
            count++;
            if(count>MXABANNER-1)
                count=0;
            im.setBackgroundResource(imagePath[count]);
            handler.postDelayed(this, 5*1000);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home, container, false);
        ib1=view.findViewById(R.id.imageButton5);
        ib2=view.findViewById(R.id.imageButton6);
        im=view.findViewById(R.id.mim1);
        ll_search=view.findViewById(R.id.ll_serach);
        lsc=view.findViewById(R.id.showCourses);
        classify=view.findViewById(R.id.classify);
        handler.postDelayed(runnable, 1000);
        TextView tv=view.findViewById(R.id.ed);
        mbtn1=view.findViewById(R.id.bt1);
        mbtn2=view.findViewById(R.id.bt2);
        mbtn3=view.findViewById(R.id.bt3);
        mbtn4=view.findViewById(R.id.bt4);
        RecyclerView recyclerView = view.findViewById(R.id.showCourses);
        ib_interest=view.findViewById(R.id.interestManager);
        ib_interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),interest.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
        DBHelper dbHelper = new DBHelper(getActivity());

       //初始化数据
        mdate=new ArrayList<String>();
        mdate=dbHelper.getFavorite(userId);
        if(mdate.size()==0)
        {
            mdate.add("计算机");
            mdate.add("英语");
            mdate.add("工学");
            mdate.add("心理学");
            mdate.add("体育");
            mdate.add("法学");
            mdate.add("教育");
            mdate.add("农林");


        }
        mbtn1.setText(mdate.get(0));
        mbtn2.setText(mdate.get(1));
        mbtn3.setText(mdate.get(2));
        mbtn4.setText(mdate.get(3));
        mbtn2.setAlpha(0.5f);
        mbtn3.setAlpha(0.5f);
        mbtn4.setAlpha(0.5f);
        //lists = new ArrayList<>();
        String kind = mbtn1.getText().toString();

        ArrayList<Video> videoArrayList = dbHelper.classification(kind);
        ImageView teacher1 = view.findViewById(R.id.teacher);
        ImageView teacher2 = view.findViewById(R.id.teacher2);
        ImageView teacher3 = view.findViewById(R.id.teacher3);
        teacher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),teacher_info.class);
                intent.putExtra("teacherID",1);
                intent.putExtra("teachername","罗翔");
                startActivity(intent);
            }
        });
        teacher2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),teacher_info.class);
                intent.putExtra("teacherID",2);
                intent.putExtra("teachername","张宇");
                startActivity(intent);
            }
        });
        teacher3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),teacher_info.class);
                intent.putExtra("teacherID",3);
                intent.putExtra("teachername","汤家凤");
                startActivity(intent);
            }
        });
        /*for(Video v:videoArrayList){
            course c = new course(v.getCover(),v.getName(),v.getIntrodution());
            lists.add(c);
        }*/
        /*for (int i=0;i<theme.length;i++){
            course c = new course(imageViews[i],theme[i],content[i]);
            lists.add(c);
        }*/
        ArrayList<Video> v = new ArrayList<Video>();
        for(int i=0;i<videoArrayList.size();i++){
            if(i==2)
                break;
            else{
                v.add(videoArrayList.get(i));
            }
        }
        // 线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        // 用于描述item的适配器
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),v);
        recyclerView.setAdapter(recyclerAdapter);



        //===============================轮播图============================================
        //设置滚动资源
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=Integer.toString(count+1);
                //Toast.makeText(getActivity(),"点击了第"+str+"张图片",Toast.LENGTH_SHORT).show();
                //此处跳转对应链接
            }
        });
        //上一张
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count=count-1;
                if(count<0)
                    count=MXABANNER-1;

                im.setBackgroundResource(imagePath[count]);

            }

        });
        //下一张
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=count+1;
                if(count>MXABANNER-1)
                    count=0;
                im.setBackgroundResource(imagePath[count]);
            }
        });
        //===============================轮播图============================================

        //===============================搜索框+分类框跳转============================================
        //搜索框
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),ac_serach.class);
                startActivity(intent);
            }

        });
        classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),ac_classify.class);
                startActivity(intent);
            }
        });
        //===============================搜索框+分类框跳转============================================


        //===============================分类筛选============================================
        //课程数据表响应
        /*lsc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l ) {
                final TextView theme=(TextView) view.findViewById(R.id.horizontalTextViewName);

                String str= theme.getText().toString();
                Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
            }
        });*/
        //设置类型分类筛选
        //第1个分类
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbtn1.setAlpha(1f);
                mbtn2.setAlpha(0.5f);
                mbtn3.setAlpha(0.5f);
                mbtn4.setAlpha(0.5f);
                //关键词2
                String str=mbtn1.getText().toString();
                //Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
                //lists = new ArrayList<>();
                DBHelper dbHelper = new DBHelper(getActivity());
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                /*for(Video v:videoArrayList){
                    course c = new course(v.getCover(),v.getName(),v.getIntrodution());
                    lists.add(c);
                }*/
                /*for (int i=0;i<theme1.length;i++){
                    course c = new course(imageViews1[i],theme1[i],content1[i]);
                    lists.add(c);
                }*/
                ArrayList<Video> v = new ArrayList<Video>();
                for(int i=0;i<videoArrayList.size();i++){
                    if(i==2)
                        break;
                    else{
                        v.add(videoArrayList.get(i));
                    }
                }
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),v);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        //第2个分类
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbtn1.setAlpha(0.5f);
                mbtn2.setAlpha(1f);
                mbtn3.setAlpha(0.5f);
                mbtn4.setAlpha(0.5f);
                String str=mbtn2.getText().toString();
                //Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
                //lists = new ArrayList<>();
                DBHelper dbHelper = new DBHelper(getActivity());
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                /*for(Video v:videoArrayList){
                    course c = new course(v.getCover(),v.getName(),v.getIntrodution());
                    lists.add(c);
                }*/
                /*for (int i=0;i<theme2.length;i++){
                    course c = new course(imageViews2[i],theme2[i],content2[i]);
                    lists.add(c);
                }*/
                ArrayList<Video> v = new ArrayList<Video>();
                for(int i=0;i<videoArrayList.size();i++){
                    if(i==2)
                        break;
                    else{
                        v.add(videoArrayList.get(i));
                    }
                }
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),v);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        //第3个分类
        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbtn1.setAlpha(0.5f);
                mbtn2.setAlpha(0.5f);
                mbtn3.setAlpha(1f);
                mbtn4.setAlpha(0.5f);
                String str=mbtn3.getText().toString();
                //Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
                //lists = new ArrayList<>();
                DBHelper dbHelper = new DBHelper(getActivity());
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                /*for(Video v:videoArrayList){
                    course c = new course(v.getCover(),v.getName(),v.getIntrodution());
                    lists.add(c);
                }*/
                /*for (int i=0;i<theme3.length;i++){
                    course c = new course(imageViews3[i],theme3[i],content3[i]);
                    lists.add(c);
                }*/
                ArrayList<Video> v = new ArrayList<Video>();
                for(int i=0;i<videoArrayList.size();i++){
                    if(i==2)
                        break;
                    else{
                        v.add(videoArrayList.get(i));
                    }
                }
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),v);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        //第4个分类
        mbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mbtn1.setAlpha(0.5f);
                mbtn2.setAlpha(0.5f);
                mbtn3.setAlpha(0.5f);
                mbtn4.setAlpha(1f);
                String str=mbtn4.getText().toString();
                //Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
                //lists = new ArrayList<>();
                DBHelper dbHelper = new DBHelper(getActivity());
                ArrayList<Video> videoArrayList = dbHelper.classification(str);
                /*for(Video v:videoArrayList){
                    course c = new course(v.getCover(),v.getName(),v.getIntrodution());
                    lists.add(c);
                }*/
                /*for (int i=0;i<theme.length;i++){
                    course c = new course(imageViews[i],theme[i],content[i]);
                    lists.add(c);
                }*/
                ArrayList<Video> v = new ArrayList<Video>();
                for(int i=0;i<videoArrayList.size();i++){
                    if(i==2)
                        break;
                    else{
                        v.add(videoArrayList.get(i));
                    }
                }
                // 线性布局管理器
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                // 用于描述item的适配器
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),v);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
        //===============================分类筛选============================================
        return view;

    }
}
