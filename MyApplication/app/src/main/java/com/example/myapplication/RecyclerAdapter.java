package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private ArrayList<Video> courseList;
    private Context context;
    private OnItemClickListener listener;
    public interface  OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public RecyclerAdapter(Context context, ArrayList<Video> courseList)
    {
        this.context = context;
        this.courseList = courseList;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    /*public RecyclerAdapter(List<course> huoyingList)
    {
        this.courseList = huoyingList;
    }*/

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_course, null, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position)
    {
        //holder.imagepath.setBackground(courseList.get(position).getImagepath());
//        String imagepath = courseList.get(position).getVideo();
//        holder.imagepath.setBackground(Drawable.createFromPath(String.valueOf(Uri.parse(imagepath))));
      //  holder.imagepath.setBackgroundResource(2131230829);
        //int a = R.drawable.course_02;
        int a = Integer.parseInt(courseList.get(position).getCover());
        holder.imagepath.setBackgroundResource(a);
        String uri = (courseList.get(position).getCover());
        holder.imagepath.setImageURI(Uri.parse(uri));
        //Toast.makeText(context.getApplicationContext(),String.valueOf(a),Toast.LENGTH_LONG).show();
        //Toast.makeText(context.getApplicationContext(), courseList.get(position).getImagepath(),Toast.LENGTH_LONG).show();
        System.out.println("path:"+courseList.get(position).getVideo());
        //holder.imagepath.setBackground(Drawable.createFromPath("/storage/emulated/0/Pictures/course_01.jpg"));
        String ev = courseList.get(position).getEmpirical_value();
        String st = courseList.get(position).getStudy_time();
        String vt = courseList.get(position).getVisit_time();
        String text = courseList.get(position).getIntrodution();
        if(!ev.equals("")){
            if(!ev.equals("")){
                holder.contents.setGravity(Gravity.BOTTOM);
                text = "经验值:"+ev+" 学习时长:"+st;
            }
        }
        if(!vt.equals("")){
            holder.contents.setGravity(Gravity.BOTTOM);
            text = "上次学习时间："+vt;
        }
        holder.name.setText(courseList.get(position).getName());
        holder.contents.setText(text);
        holder.imagepath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, holder.name.getText().toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(context,course_info.class);
                intent.putExtra("Ser",courseList.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return courseList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        ImageView imagepath;
        TextView contents;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.name = itemView.findViewById(R.id.horizontalTextViewName);
            this.imagepath = itemView.findViewById(R.id.horizontalImageView);
            this.contents = itemView.findViewById(R.id.horizontalTextViewContent);
        }
    }
}

