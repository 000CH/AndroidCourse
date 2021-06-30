package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    //声明四个Tab的布局文件
    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private LinearLayout mTab3;

    //声明四个Tab的ImageButton
    private ImageButton mImg1;
    private ImageButton mImg2;
    private ImageButton mImg3;

    //声明四个Tab分别对应的Fragment
    private Fragment mFrag1;
    private Fragment mFrag2;
    private Fragment mFrag3;

    private int userId ;
    private int identify ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userId= getIntent().getIntExtra("userId",1);
        identify= getIntent().getIntExtra("identify",1);
        //下面6个是初始化信息
//        initUserInfo(userId);
//        initFavorite(userId);
//        initdb2();
//        initdb3();
//        initdb4();
//        initdb();




//        initdb20();

////        initdb5();
////        initdb6();
        initViews();//初始化控件
        initEvents();//初始化事件
        selectTab(0);//默认选中第一个Tab
    }
    private void initFavorite(int userId){

        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues contentValues1 = new ContentValues();
        ContentValues contentValues2= new ContentValues();
        ContentValues contentValues3 = new ContentValues();
        contentValues.put("userID",userId);
        contentValues.put("kind","计算机");
        dbHelper.insert("favorite",contentValues);

        contentValues1.put("userID",userId);
        contentValues1.put("kind","英语");
        dbHelper.insert("favorite",contentValues1);

        contentValues2.put("userID",userId);
        contentValues2.put("kind","工学");
        dbHelper.insert("favorite",contentValues2);

        contentValues3.put("userID",userId);
        contentValues3.put("kind","心理学");
        dbHelper.insert("favorite",contentValues3);
    }
    private void initUserInfo(int userId){

        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID",userId);
        contentValues.put("nickname","nnn");
        contentValues.put("name","555");
        contentValues.put("age","666");
        contentValues.put("sex","88");
        contentValues.put("head","99");
        dbHelper.insert("person_info",contentValues);
    }

    //初始化用户个人信息
    private void initdb20() {
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("userID",1);
        contentValues.put("nickname","kitty");
        contentValues.put("head",String.valueOf(R.drawable.me));
        dbHelper.insert("person_info",contentValues);

        ContentValues contentValues005 = new ContentValues();
        contentValues005.put("userID",2);
        contentValues005.put("nickname","小明");
        contentValues005.put("head",String.valueOf(R.drawable.teacher01));
        dbHelper.insert("person_info",contentValues005);
    }
    //初始化课程信息
    private void initdb() {
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb=dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(date);//获取String类型的时间
        String videouri = "android.resource://" + getPackageName() + "/" + R.raw.video01;
        String coveruri = String.valueOf(R.drawable.course_01);
        contentValues.put("name","计算机组成原理");
        contentValues.put("kind","计算机");
        contentValues.put("video",videouri);
        contentValues.put("cover",coveruri);
        contentValues.put("introdution","该课程介绍了计算机的基本组成原理和内部工作机制。全课程共分8章，主要内容分成两个部分：第1、2章介绍了计算机的基础知识；第3-8章介绍了计算机的各子系统（包括运算器、存储器、控制器、外部设备和输入输出子系统等）的基本组成原理、设计方法、相互关系以及各子系统互相连接构成整机系统的技术。");
        contentValues.put("uploaderID",1);
        contentValues.put("time",time);
        dbHelper.insert("class",contentValues);
        ContentValues contentValues02 = new ContentValues();
        Date date02 = new Date();//获取当前的日期
        SimpleDateFormat df02 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time02 = df02.format(date02);//获取String类型的时间
        String videouri02 = "android.resource://" + getPackageName() + "/" + R.raw.video02;
        String coveruri02 = String.valueOf(R.drawable.course_02);
        contentValues02.put("name","数据结构");
        contentValues02.put("kind","计算机");
        contentValues02.put("video",videouri02);
        contentValues02.put("cover",coveruri02);
        contentValues02.put("introdution","数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。通常情况下，精心选择的数据结构可以带来更高的运行或者存储效率。数据结构往往同高效的检索算法和索引技术有关。");
        contentValues02.put("uploaderID",1);
        contentValues02.put("time",time02);
        dbHelper.insert("class",contentValues02);
        ContentValues contentValues03 = new ContentValues();
        Date date03 = new Date();//获取当前的日期
        SimpleDateFormat df03 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time03 = df03.format(date03);//获取String类型的时间
        String videouri03 = "android.resource://" + getPackageName() + "/" + R.raw.video03;
        String coveruri03 = String.valueOf(R.drawable.course_03);
        contentValues03.put("name","概率论");
        contentValues03.put("kind","工学");
        contentValues03.put("video",videouri03);
        contentValues03.put("cover",coveruri03);
        contentValues03.put("introdution","概率论，是研究随机现象数量规律的数学分支。随机现象是相对于决定性现象而言的，在一定条件下必然发生某一结果的现象称为决定性现象。");
        contentValues03.put("uploaderID",2);
        contentValues03.put("time",time03);
        dbHelper.insert("class",contentValues03);
        ContentValues contentValues04 = new ContentValues();
        Date date04 = new Date();//获取当前的日期
        SimpleDateFormat df04 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time04 = df04.format(date04);//获取String类型的时间
        String videouri04 = "android.resource://" + getPackageName() + "/" + R.raw.video04;
        String coveruri04 = String.valueOf(R.drawable.course_04);
        contentValues04.put("name","微积分");
        contentValues04.put("kind","工学");
        contentValues04.put("video",videouri04);
        contentValues04.put("cover",coveruri04);
        contentValues04.put("introdution","微积分（Calculus），数学概念，是高等数学中研究函数的微分(Differentiation)、积分(Integration)以及有关概念和应用的数学分支。它是数学的一个基础学科，内容主要包括极限、微分学、积分学及其应用。微分学包括求导数的运算，是一套关于变化率的理论。它使得函数、速度、加速度和曲线的斜率等均可用一套通用的符号进行讨论。");
        contentValues04.put("uploaderID",2);
        contentValues04.put("time",time04);
        dbHelper.insert("class",contentValues04);
        ContentValues contentValues05 = new ContentValues();
        Date date05 = new Date();//获取当前的日期
        SimpleDateFormat df05 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time05 = df05.format(date05);//获取String类型的时间
        String videouri05 = "android.resource://" + getPackageName() + "/" + R.raw.video05;
        String coveruri05 = String.valueOf(R.drawable.course_05);
        contentValues05.put("name","刘晓燕讲英语");
        contentValues05.put("kind","英语");
        contentValues05.put("video",videouri05);
        contentValues05.put("cover",coveruri05);
        contentValues05.put("introdution","英语考试是为高等学校和科研机构招收硕士研究生而设置的具有选拔性质的全国统一入学考试科目，其目的是科学、公正、有效地测试考生对英语语言的运用能力，评价的标准时高等学校非英语专业本科毕业生所能达到的及格或及格以上水平，以保证被录取者具有一定的英语水平，并有利于各高等学校和科研院所在专业上择优选拔。");
        contentValues05.put("uploaderID",3);
        contentValues05.put("time",time05);
        dbHelper.insert("class",contentValues05);
        ContentValues contentValues06 = new ContentValues();
        Date date06 = new Date();//获取当前的日期
        SimpleDateFormat df06 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time06 = df06.format(date06);//获取String类型的时间
        String videouri06 = "android.resource://" + getPackageName() + "/" + R.raw.video06;
        String coveruri06 = String.valueOf(R.drawable.course_06);
        contentValues06.put("name","四级英语");
        contentValues06.put("kind","英语");
        contentValues06.put("video",videouri06);
        contentValues06.put("cover",coveruri06);
        contentValues06.put("introdution","英语专业四级考试（TEM-4，Test for English Majors-Band 4），全称为全国高校英语专业四级考试。自1991年起由中国大陆教育部实行，考察全国综合性大学英语专业学生。考试内容涵盖英语听、说、读、写四个方面。");
        contentValues06.put("uploaderID",3);
        contentValues06.put("time",time06);
        dbHelper.insert("class",contentValues06);
        ContentValues contentValues07 = new ContentValues();
        Date date07 = new Date();//获取当前的日期
        SimpleDateFormat df07 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time07 = df07.format(date07);//获取String类型的时间
        String videouri07 = "android.resource://" + getPackageName() + "/" + R.raw.video07;
        String coveruri07 = String.valueOf(R.drawable.course_07);
        contentValues07.put("name","清华大学心理学");
        contentValues07.put("kind","心理学");
        contentValues07.put("video",videouri07);
        contentValues07.put("cover",coveruri07);
        contentValues07.put("introdution","心理学是一门研究人类心理现象及其影响下的精神功能和行为活动的科学，兼顾突出的理论性和应用（实践）性。");
        contentValues07.put("uploaderID",4);
        contentValues07.put("time",time07);
        dbHelper.insert("class",contentValues07);
        ContentValues contentValues08 = new ContentValues();
        Date date08 = new Date();//获取当前的日期
        SimpleDateFormat df08 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time08 = df08.format(date08);//获取String类型的时间
        String videouri08 = "android.resource://" + getPackageName() + "/" + R.raw.video08;
        String coveruri08 = String.valueOf(R.drawable.course_08);
        contentValues08.put("name","耶鲁大学心理学导论");
        contentValues08.put("kind","心理学");
        contentValues08.put("video",videouri08);
        contentValues08.put("cover",coveruri08);
        contentValues08.put("introdution","心理学家从事基础研究的目的是描述、解释、预测和影响行为。应用心理学家还有第五个目的——提高人类生活的质量。这些目标构成了心理学事业的基础。");
        contentValues08.put("uploaderID",4);
        contentValues08.put("time",time08);
        dbHelper.insert("class",contentValues08);
    }
    //初始化兴趣表，订阅课程表
    private void initdb2(){
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb=dbHelper.getWritableDatabase();
        //插入兴趣表
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID",1);
        contentValues.put("kind","计算机");
        dbHelper.insert("favorite",contentValues);
        ContentValues contentValues02 = new ContentValues();
        contentValues02.put("userID",1);
        contentValues02.put("kind","英语");
        dbHelper.insert("favorite",contentValues02);
        ContentValues contentValues03 = new ContentValues();
        contentValues03.put("userID",1);
        contentValues03.put("kind","工学");
        dbHelper.insert("favorite",contentValues03);
        ContentValues contentValues04 = new ContentValues();
        contentValues04.put("userID",1);
        contentValues04.put("kind","心理学");
        dbHelper.insert("favorite",contentValues04);

        ContentValues contentValues01 = new ContentValues();
        contentValues01.put("userID",2);
        contentValues01.put("kind","工学");
        dbHelper.insert("favorite",contentValues01);
        ContentValues contentValues002 = new ContentValues();
        contentValues002.put("userID",2);
        contentValues002.put("kind","心理学");
        dbHelper.insert("favorite",contentValues002);
        ContentValues contentValues003 = new ContentValues();
        contentValues003.put("userID",2);
        contentValues003.put("kind","计算机");
        dbHelper.insert("favorite",contentValues003);
        ContentValues contentValues004 = new ContentValues();
        contentValues004.put("userID",2);
        contentValues004.put("kind","英语");
        dbHelper.insert("favorite",contentValues004);

        //插入订阅课程表
        ContentValues contentValues000 = new ContentValues();
        contentValues000.put("userID",1);
        contentValues000.put("classID",1);
        contentValues000.put("empirical_value","200");
        contentValues000.put("study_time","1h50min");
        dbHelper.insert("subscribtion",contentValues000);
        ContentValues contentValues001 = new ContentValues();
        contentValues001.put("userID",1);
        contentValues001.put("classID",2);
        contentValues001.put("empirical_value","50");
        contentValues001.put("study_time","45min");
        dbHelper.insert("subscribtion",contentValues001);
        ContentValues contentValues102 = new ContentValues();
        contentValues102.put("userID",1);
        contentValues102.put("classID",3);
        contentValues102.put("empirical_value","150");
        contentValues102.put("study_time","1h05min");
        dbHelper.insert("subscribtion",contentValues002);
        ContentValues contentValues103 = new ContentValues();
        contentValues103.put("userID",1);
        contentValues103.put("classID",4);
        contentValues103.put("empirical_value","150");
        contentValues103.put("study_time","1h05min");
        dbHelper.insert("subscribtion",contentValues003);

        ContentValues contentValues100 = new ContentValues();
        contentValues100.put("userID",2);
        contentValues100.put("classID",1);
        contentValues100.put("empirical_value","200");
        contentValues100.put("study_time","1h50min");
        dbHelper.insert("subscribtion",contentValues100);
        ContentValues contentValues101 = new ContentValues();
        contentValues101.put("userID",2);
        contentValues101.put("classID",2);
        contentValues101.put("empirical_value","50");
        contentValues101.put("study_time","45min");
        dbHelper.insert("subscribtion",contentValues101);
        //插入个人信息表
//        ContentValues contentValues005 = new ContentValues();
//        String headuri = "android.resource://" + getPackageName() + "/" + R.drawable.teacher01;
//        contentValues005.put("userID",1);
//        contentValues005.put("nickname","小明");
//        contentValues005.put("head",headuri);
    }
    //学习历史表
    private void initdb3(){
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb=dbHelper.getWritableDatabase();
        //插入学习历史表
        ContentValues contentValues000 = new ContentValues();
        Date date0 = new Date();//获取当前的日期
        SimpleDateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time0 = df0.format(date0);//获取String类型的时间
        contentValues000.put("userID",1);
        contentValues000.put("classID",5);
        contentValues000.put("time",time0);
        dbHelper.insert("history",contentValues000);
        ContentValues contentValues001 = new ContentValues();
        Date date1 = new Date();//获取当前的日期
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time1 = df1.format(date1);//获取String类型的时间
        contentValues001.put("userID",1);
        contentValues001.put("classID",6);
        contentValues001.put("time",time0);
        dbHelper.insert("history",contentValues001);
        ContentValues contentValues002 = new ContentValues();
        Date date2 = new Date();//获取当前的日期
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time2 = df2.format(date2);//获取String类型的时间
        contentValues002.put("userID",1);
        contentValues002.put("classID",7);
        contentValues002.put("time",time2);
        dbHelper.insert("history",contentValues002);
        ContentValues contentValues003 = new ContentValues();
        Date date3 = new Date();//获取当前的日期
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time3 = df3.format(date3);//获取String类型的时间
        contentValues003.put("userID",1);
        contentValues003.put("classID",8);
        contentValues003.put("time",time3);
        dbHelper.insert("history",contentValues003);

        ContentValues contentValues100 = new ContentValues();
        Date date10 = new Date();//获取当前的日期
        SimpleDateFormat df10 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time10 = df10.format(date10);//获取String类型的时间
        contentValues100.put("userID",2);
        contentValues100.put("classID",1);
        contentValues100.put("time",time10);
        dbHelper.insert("history",contentValues100);
        ContentValues contentValues101 = new ContentValues();
        Date date11 = new Date();//获取当前的日期
        SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time11 = df11.format(date11);//获取String类型的时间
        contentValues101.put("userID",2);
        contentValues101.put("classID",7);
        contentValues101.put("time",time11);
        dbHelper.insert("history",contentValues101);
        ContentValues contentValues102 = new ContentValues();
        Date date12 = new Date();//获取当前的日期
        SimpleDateFormat df12 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time12 = df12.format(date12);//获取String类型的时间
        contentValues102.put("userID",2);
        contentValues102.put("classID",4);
        contentValues102.put("time",time12);
        dbHelper.insert("history",contentValues102);
    }
    //收藏表
    private void initdb4() {
        final DBHelper dbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb=dbHelper.getWritableDatabase();
        //插入收藏表
        ContentValues contentValues000 = new ContentValues();
        contentValues000.put("userID",1);
        contentValues000.put("classID",6);
        dbHelper.insert("star",contentValues000);
        ContentValues contentValues001 = new ContentValues();
        contentValues001.put("userID",1);
        contentValues001.put("classID",2);
        dbHelper.insert("star",contentValues001);
        ContentValues contentValues002 = new ContentValues();
        contentValues002.put("userID",1);
        contentValues002.put("classID",3);
        dbHelper.insert("star",contentValues002);
        ContentValues contentValues009 = new ContentValues();
        contentValues009.put("userID",1);
        contentValues009.put("classID",9);
        dbHelper.insert("star",contentValues009);

        ContentValues contentValues100 = new ContentValues();
        contentValues100.put("userID",2);
        contentValues100.put("classID",1);
        dbHelper.insert("star",contentValues100);
        ContentValues contentValues101 = new ContentValues();
        contentValues101.put("userID",2);
        contentValues101.put("classID",3);
        dbHelper.insert("star",contentValues101);
        ContentValues contentValues102 = new ContentValues();
        contentValues102.put("userID",2);
        contentValues102.put("classID",2);
        dbHelper.insert("star",contentValues102);
        ContentValues contentValues109 = new ContentValues();
        contentValues109.put("userID",2);
        contentValues109.put("classID",4);
        dbHelper.insert("star",contentValues109);
    }
    private void initEvents() {
        //初始化四个Tab的点击事件
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);

    }

    private void initViews() {
        //初始化个Tab的布局文件
        mTab1 = (LinearLayout) findViewById(R.id.id_tab1);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab2);
        mTab3 = (LinearLayout) findViewById(R.id.id_tab3);


        //初始化ImageButton
        mImg1 = (ImageButton) findViewById(R.id.id_tab_img1);
        mImg2 = (ImageButton) findViewById(R.id.id_tab_img2);
        mImg3 = (ImageButton) findViewById(R.id.id_tab_img3);


    }

    //处理Tab的点击事件
    @Override
    public void onClick(View v) {
        resetImgs(); //先将四个ImageButton置为灰色
        switch (v.getId()) {
            case R.id.id_tab1:
                selectTab(0);
                break;
            case R.id.id_tab2:
                selectTab(1);
                break;
            case R.id.id_tab3:
                selectTab(2);
                break;

        }

    }

    //进行选中Tab的处理
    private void selectTab(int i) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //获取FragmentTransaction对象
        FragmentTransaction transaction = manager.beginTransaction();
        //先隐藏所有的Fragment
        hideFragments(transaction);


        switch (i) {
            //当选中点击的是第一页的Tab时
            case 0:
                //设置第一页的ImageButton为绿色
                mImg1.setImageResource(R.drawable.home);
                //如果第一页对应的Fragment没有实例化，则进行实例化，并显示出来
                if (mFrag1 == null) {
                    mFrag1 = new pageFragment_home(userId);
                    transaction.add(R.id.id_content, mFrag1);
                } else {
                    //如果第一页对应的Fragment已经实例化，则直接显示出来
                    transaction.show(mFrag1);
                }
                break;
            case 1:
                mImg2.setImageResource(R.drawable.learn);
                mFrag2 = new pageFragment_lean(userId);
                transaction.add(R.id.id_content, mFrag2);
                transaction.show(mFrag2);
                /*if (mFrag2 == null) {
                    mFrag2 = new pageFragment_lean();
                    transaction.add(R.id.id_content, mFrag2);
                } else {
                    transaction.show(mFrag2);
                }*/
                break;
            case 2:
                mImg3.setImageResource(R.drawable.self);
                if (mFrag3 == null) {
                    mFrag3 = new pageFragment_self(userId,identify);
                    transaction.add(R.id.id_content, mFrag3);
                } else {
                    transaction.show(mFrag3);
                }
                break;

        }
        //不要忘记提交事务
        transaction.commit();
    }

    //将四个的Fragment隐藏
    private void hideFragments(FragmentTransaction transaction) {
        if (mFrag1 != null) {
            transaction.hide(mFrag1);
        }
        if (mFrag2 != null) {
            transaction.hide(mFrag2);
        }
        if (mFrag3 != null) {
            transaction.hide(mFrag3);
        }

    }

    //将imageButton置为灰色
    private void resetImgs() {
        mImg1.setImageResource(R.drawable.home_unselected);
        mImg2.setImageResource(R.drawable.learn_unselected);
        mImg3.setImageResource(R.drawable.self_unselected);

    }
}






