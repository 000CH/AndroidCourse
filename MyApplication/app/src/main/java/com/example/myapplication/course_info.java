package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class course_info extends AppCompatActivity {
    private VideoView videoView;
    private TextView nameT, teacherT, infoT, startTimeT;
    private MediaController mc;
    private ImageView backImg;
    private int userID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        List<String> permissionList=new ArrayList<>();
        if (ContextCompat.checkSelfPermission(course_info.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(course_info.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(course_info.this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.INTERNET);
        }if (ContextCompat.checkSelfPermission(course_info.this,Manifest.permission.MANAGE_DOCUMENTS)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.MANAGE_DOCUMENTS);
        }
        if (!permissionList.isEmpty()){
            String [] permissions=permissionList.toArray(new String [permissionList.size()]);
            ActivityCompat.requestPermissions(course_info.this,permissions,1);
        }
        videoView = findViewById(R.id.course_video);
        nameT = findViewById(R.id.course_name);
        teacherT = findViewById(R.id.course_teacher);
        infoT = findViewById(R.id.course_info);
        startTimeT = findViewById(R.id.course_startTime);
        backImg = findViewById(R.id.back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String videourl = "android.resource://" + getPackageName() + "/" + R.raw.video02;
        Video video = (Video)getIntent().getSerializableExtra("Ser");
        //videourl = video.getVideo();
        int classID = video.getId();
        videourl = video.getVideo();
        String name = video.getName();
        String kind = video.getKind();
        String introduction = video.getIntrodution();
        int uploaderID = video.getUploaderID();
        String time = video.getTime();
        String empirical_value = video.getEmpirical_value();
        String study_time = video.getStudy_time();
        String visit_time = video.getVisit_time();
        startVideo(videourl);
        nameT.setText(nameT.getText()+name);
        teacherT.setText(teacherT.getText()+String.valueOf(uploaderID));
        infoT.setText(infoT.getText()+introduction);
        startTimeT.setText(startTimeT.getText()+time);
        Button joinbt = findViewById(R.id.join);
        DBHelper dbHelper = new DBHelper(course_info.this);
        Boolean flag = dbHelper.isSubs(userID,classID);

        Toast.makeText(course_info.this,String.valueOf(userID)+"\t"+String.valueOf(classID),Toast.LENGTH_LONG).show();
        if(flag == true){
            joinbt.setAlpha(0.5f);
            joinbt.setText("取消订阅");
        }
        else if(flag == false){
            joinbt.setAlpha(1f);
            joinbt.setText("加入课程");
        }
        dbHelper.close();
        joinbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = joinbt.getText().toString();
                if(text.equals("加入课程")){
                    joinbt.setAlpha(0.5f);
                    joinbt.setText("取消订阅");
                    //数据库插入
                    DBHelper dbHelper = new DBHelper(course_info.this);
                    dbHelper.insertSubs(userID,classID);
                    dbHelper.close();
                }
                else if(text.equals("取消订阅")){
                    joinbt.setAlpha(1f);
                    joinbt.setText("加入课程");
                    //数据库删除
                    DBHelper dbHelper = new DBHelper(course_info.this);
                    dbHelper.deleteSubs(userID,classID);
                    dbHelper.close();
                }
                Toast.makeText(course_info.this,"asdasdd",Toast.LENGTH_LONG).show();
            }
        });
        /*nameT.setText(nameT.getText()+"Android开发与实践");
        teacherT.setText(teacherT.getText()+"王峥老师");
        infoT.setText(infoT.getText()+"\nAndroid开发是使用Java语言对移动端开发技术的简单介绍和相关例子功能的实操。" +
                "了解Android平台和应用的概况，学习核心知识，快速掌握应用程序开发所需的基础。掌握开发环节中的IDE环境、控件的属性和使用方法。" +
                "从案例出发，掌握完整的Android软件开发的流程和技术架构方法，包括：开发环境的搭建、应用程序的基本结构、界面组件、互联网应用编程、数据库存取、系统服务等；通过工程案例，模拟真实项目开发，掌握实用的测试、调试技术；");
        startTimeT.setText(startTimeT.getText()+"2021年6月1日");*/
    }
    private void startVideo(String uri){
        mc = new MediaController(course_info.this);
        videoView.setVideoURI(Uri.parse(uri));
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        videoView.requestFocus();
        try{
            videoView.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(course_info.this, "视频播放完毕",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(videoView != null){
            videoView = null;
        }
        super.onDestroy();
    }

}