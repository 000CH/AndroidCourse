package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class pageFragment_lean extends Fragment {
    @Nullable
    private ListView listView;
    private ListView listView1;
    //private List<item> list;
    private List<history_item> history_list;
    private int userID=1;

    public pageFragment_lean(int userId) {
        this.userID = userId;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.tab_learn, container, false);
            RecyclerView recyclerView01 = view.findViewById(R.id.subCourses);
            RecyclerView recyclerView02 = view.findViewById(R.id.hisCourses);
            RecyclerView recyclerView03 = view.findViewById(R.id.starCourses);
            ImageButton imageButton = view.findViewById(R.id.calender);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("userID",userID);
                    intent.setClass(getActivity(), MainActivity1.class);
                    startActivity(intent);
                }
            });
            //Toast.makeText(getActivity(),Integer.toString(userID),Toast.LENGTH_LONG).show();
            DBHelper dbHelper = new DBHelper(getActivity());
            ArrayList<Video> videoSubList = dbHelper.getsubs(userID);
            ArrayList<Video> videohisList = dbHelper.gethis(userID);
            ArrayList<Video> videoStarList = dbHelper.getstar(userID);
            dbHelper.close();

            // 线性布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView01.setLayoutManager(linearLayoutManager);
            // 用于描述item的适配器
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(),videoSubList);
            recyclerView01.setAdapter(recyclerAdapter);


            // 线性布局管理器
            LinearLayoutManager linearLayoutManager02 = new LinearLayoutManager(getActivity());
            recyclerView02.setLayoutManager(linearLayoutManager02);
            // 用于描述item的适配器
            RecyclerAdapter recyclerAdapter02 = new RecyclerAdapter(getActivity(),videohisList);
            recyclerView02.setAdapter(recyclerAdapter02);

            // 线性布局管理器
            LinearLayoutManager linearLayoutManager03 = new LinearLayoutManager(getActivity());
            recyclerView03.setLayoutManager(linearLayoutManager03);
            // 用于描述item的适配器
            RecyclerAdapter recyclerAdapter03 = new RecyclerAdapter(getActivity(),videoStarList);
            recyclerView03.setAdapter(recyclerAdapter03);
            return view;
        }
    }





