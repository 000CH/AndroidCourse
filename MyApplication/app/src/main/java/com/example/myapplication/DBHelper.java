package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * 在数据库里创建4张表
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final String db_name = "data1150.db";//数据库名称
    private static final int version = 1;//数据库版本
    private SQLiteDatabase db;
    private static final String table_user_name="user";//用户表
    private static final String table_class_name="class";//课程表
    private static final String table_hist_name="history";//历史记录表
    private static final String table_info_name="person_info";//个人信息表
    private static final String table_fav_name="favorite";//爱好表
    private static final String table_subs_name="subscribtion";//订阅表
    private static final String table_star_name="star";
    public DBHelper(Context context) {
        super(context, db_name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("create table user(" +
                "_id integer primary key autoincrement,name text,pwd text,identity integer,question text,answer text)");
        db.execSQL("create table class(" +
                "_id integer primary key autoincrement,name text,kind text,video text,cover text," +
                "introdution text,uploaderID integer,time text)");
        db.execSQL("create table history(userID integer,classID integer,time text)");
        db.execSQL("create table person_info(userID integer primary key,name text,nickname text,head text,age text,sex text)");
        db.execSQL("create table favorite(userID integer,kind text)");
        db.execSQL("create table subscribtion(userID integer,classID integer,empirical_value text,study_time text)");
        db.execSQL("create table star(userID integer,classID integer)");
        db.execSQL("create table schedules(id Integer primary key autoincrement," +     //id自增,只支持integer不支持int
                        "userID int,"+
                        "scheduleDetail varchar(50)," +
                        "time varchar(30)" +
                        ")");



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insert(String table_name,ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(table_name,null,values);
        db.close();
    }
    //create table class("_id integer primary key autoincrement,name text,kind text,video text,cover text,introdution text,uploaderID integer,time text)
    public void close() {
        if(db!=null)
            db.close();
    }
    public Cursor query(String table_name){
        SQLiteDatabase db=getWritableDatabase();
        String sql="select * from user where username=? and password=?";
        String name="",pwd="",identity="";
        Cursor cursor=db.query(table_name,new String[]{name,pwd,identity},null,null,null,null,null);
        return cursor;
    }
    public ArrayList<Video> searchByname(String text){
        String sql="select * from class where name=?";
        ArrayList<Video> videoArrayList = traverse(sql,text);//存放查到的所有视频
        return videoArrayList;
    }
    public ArrayList<Video> classification(String text){
        String sql="select * from class where kind=?";
        ArrayList<Video> videoArrayList = traverse(sql,text);//存放查到的所有视频
        return videoArrayList;
    }
    public ArrayList<Video> getTeacherclass(int teacherID){
        String sql = "select * from class where uploaderID=?";
        ArrayList<Video> videoArrayList = traverse(sql,String.valueOf(teacherID));//存放查到的所有视频
        return videoArrayList;
    }
    public ArrayList<Video> getsubs(int userID){
        String sql="select * from subscribtion where userID=?";
        SQLiteDatabase sdb=getReadableDatabase();
        //ArrayList<Integer> classIDList = new ArrayList<Integer>();
        ArrayList<Video> videoArrayList = new ArrayList<Video>();
        String text = String.valueOf(userID);
        Cursor cursor = sdb.rawQuery(sql,new String[]{text});
        while(cursor.moveToNext()){
            int classID = cursor.getInt(1);
            String emval = cursor.getString(2);
            String studytime = cursor.getString(3);
            String sql1="select * from class where _id=?";
            Cursor cursor1 = sdb.rawQuery(sql1,new String[]{String.valueOf(classID)});
            if(cursor1.moveToFirst()){
                Video video = new Video();
                video.setId(cursor1.getInt(0));
                //video.setName(cursor.getColumnIndex("name"));
                video.setName(cursor1.getString(1));
                video.setKind(cursor1.getString(2));
                video.setVideo(cursor1.getString(3));
                video.setCover(cursor1.getString(4));
                video.setIntrodution(cursor1.getString(5));
                video.setUploaderID(cursor1.getInt(6));
                video.setTime(cursor1.getString(7));
                video.setEmpirical_value(emval);
                video.setStudy_time(studytime);
                videoArrayList.add(video);
            }
        }
        return videoArrayList;
    }
    public ArrayList<Video> gethis(int userID){
        String sql="select * from history where userID=?";
        SQLiteDatabase sdb=getReadableDatabase();
        //ArrayList<Integer> classIDList = new ArrayList<Integer>();
        ArrayList<Video> videoArrayList = new ArrayList<Video>();
        String text = String.valueOf(userID);
        Cursor cursor = sdb.rawQuery(sql,new String[]{text});
        while(cursor.moveToNext()){
            int classID = cursor.getInt(1);
            String visit_time = cursor.getString(2);
            String sql1="select * from class where _id=?";
            Cursor cursor1 = sdb.rawQuery(sql1,new String[]{String.valueOf(classID)});
            if(cursor1.moveToFirst()){
                Video video = new Video();
                video.setId(cursor1.getInt(0));
                //video.setName(cursor.getColumnIndex("name"));
                video.setName(cursor1.getString(1));
                video.setKind(cursor1.getString(2));
                video.setVideo(cursor1.getString(3));
                video.setCover(cursor1.getString(4));
                video.setIntrodution(cursor1.getString(5));
                video.setUploaderID(cursor1.getInt(6));
                video.setTime(cursor1.getString(7));
                video.setVisit_time(visit_time);
                videoArrayList.add(video);
            }
        }
        return videoArrayList;
    }
    public ArrayList<Video> getstar(int userID){
        String sql="select * from star where userID=?";
        SQLiteDatabase sdb=getReadableDatabase();
        //ArrayList<Integer> classIDList = new ArrayList<Integer>();
        ArrayList<Video> videoArrayList = new ArrayList<Video>();
        String text = String.valueOf(userID);
        Cursor cursor = sdb.rawQuery(sql,new String[]{text});
        while(cursor.moveToNext()){
            int classID = cursor.getInt(1);
            String sql1="select * from class where _id=?";
            Cursor cursor1 = sdb.rawQuery(sql1,new String[]{String.valueOf(classID)});
            if(cursor1.moveToFirst()){
                Video video = new Video();
                video.setId(cursor1.getInt(0));
                //video.setName(cursor.getColumnIndex("name"));
                video.setName(cursor1.getString(1));
                video.setKind(cursor1.getString(2));
                video.setVideo(cursor1.getString(3));
                video.setCover(cursor1.getString(4));
                video.setIntrodution(cursor1.getString(5));
                video.setUploaderID(cursor1.getInt(6));
                video.setTime(cursor1.getString(7));
                videoArrayList.add(video);
            }
        }
        return videoArrayList;
    }
    public void insertSubs(int userID,int classID){
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID",userID);
        contentValues.put("classID",classID);
        contentValues.put("empirical_value","0");
        contentValues.put("study_time","0");
        SQLiteDatabase db = getWritableDatabase();
        db.insert("subscribtion",null,contentValues);
        db.close();
    }
    public void deleteSubs(int userID,int classID){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "userID=? and classID=?";
        db.delete("subscribtion",sql,new String[]{String.valueOf(userID),String.valueOf(classID)});
        db.close();
    }
    public boolean isSubs(int userID,int classID){
        String sql = "select * from subscribtion where userID=? and classID=?";
        SQLiteDatabase sdb=getReadableDatabase();
        Cursor cursor = sdb.rawQuery(sql,new String[]{String.valueOf(userID),String.valueOf(classID)});
        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }
    public String getinfo(int userID){
//        List<String>  list = new ArrayList<>();
        String res = "";
        String sql = "select * from person_info where userID=?";
        SQLiteDatabase sdb=getReadableDatabase();
        Cursor cursor = sdb.rawQuery(sql,new String[]{String.valueOf(userID)});
        if(cursor.moveToFirst()){
            String nickname = cursor.getString(2);
            String head = cursor.getString(3);
            res = nickname+" "+head;
//            list.add(res);
        }
        return res;
    }
    public ArrayList<Video> traverse(String sql, String text){
        //final DBHelper dbOpenHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase sdb=getReadableDatabase();
        Cursor cursor = sdb.rawQuery(sql,new String[]{text});
        Video video = null;
        ArrayList<Video> videoArrayList = new ArrayList<Video>();//存放查到的所有视频
        while (cursor.moveToNext()){
            video = new Video();
            video.setId(cursor.getInt(0));
            //video.setName(cursor.getColumnIndex("name"));
            video.setName(cursor.getString(1));
            video.setKind(cursor.getString(2));
            video.setVideo(cursor.getString(3));
            video.setCover(cursor.getString(4));
            video.setIntrodution(cursor.getString(5));
            video.setUploaderID(cursor.getInt(6));
            video.setTime(cursor.getString(7));
            videoArrayList.add(video);
        }
        return videoArrayList;
    }
    public ArrayList<String> getCalender(int userID,String date){
        String sql = "select * from schedules where userID=? and time=?";
        SQLiteDatabase sdb=getReadableDatabase();
        ArrayList<String> ss = new ArrayList<>();
        Cursor cursor = sdb.rawQuery(sql,new String[]{String.valueOf(userID),date});
        while(cursor.moveToNext()){
            String thing = cursor.getString(2);
            ss.add(thing);
        }
        return ss;
    }
    public void UpdateCalender(int userID,String date,String details,String info){
        SQLiteDatabase sdb=getWritableDatabase();
        sdb.execSQL("UPDATE schedules SET scheduleDetail=? WHERE scheduleDetail=? and userID=? and time=?",
                new String[]{info,details,String.valueOf(userID),date});
        sdb.close();
    }

    public ArrayList<String> getFavorite(int userID){
        String sql = "select * from favorite where userID=?";
        SQLiteDatabase sdb=getReadableDatabase();
        ArrayList<String> ss = new ArrayList<>();
        Cursor cursor = sdb.rawQuery(sql,new String[]{String.valueOf(userID)});
        while(cursor.moveToNext()){
            String thing = cursor.getString(1);
            ss.add(thing);
        }
        return ss;
    }
    public void deletefavorite(int userID){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "userID=? ";
        db.delete("favorite",sql,new String[]{String.valueOf(userID)});
        db.close();
    }

    public void deleteCalender(int userID,String date,String details){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "userID=? and scheduleDetail=? and time=?";
        db.delete("schedules",sql,new String[]{String.valueOf(userID),details,date});
        db.close();
    }
    public void UpdateUserinfo(int userID,String name,String nickname,String head,String age,String sex){
        SQLiteDatabase sdb=getWritableDatabase();
        /*sdb.execSQL("UPDATE person_info SET name=? and nickname=? and head=? and age=? and sex=? WHERE userID=?",
                new String[]{name,nickname,head,age,sex,String.valueOf(userID)});*/
        sdb.execSQL("UPDATE person_info SET name=? WHERE userID=?",
                new String[]{name,String.valueOf(userID)});
        sdb.execSQL("UPDATE person_info SET nickname=? WHERE userID=?",
                new String[]{nickname,String.valueOf(userID)});
        sdb.execSQL("UPDATE person_info SET head=? WHERE userID=?",
                new String[]{head,String.valueOf(userID)});
        sdb.execSQL("UPDATE person_info SET age=? WHERE userID=?",
                new String[]{age,String.valueOf(userID)});
        sdb.execSQL("UPDATE person_info SET sex=? WHERE userID=?",
                new String[]{sex,String.valueOf(userID)});
        sdb.close();
    }
    public Person getUserinfo(int userID){
        SQLiteDatabase sdb=getReadableDatabase();
        String sql = "select * from person_info where userID=?";
        Cursor cursor = sdb.rawQuery(sql,new String[]{String.valueOf(userID)});
        Person person = new Person();
        if(cursor.moveToFirst()){
            person.setUserID(cursor.getInt(0));
            person.setName(cursor.getString(1));
            person.setNickname(cursor.getString(2));
            person.setHead(cursor.getString(3));
            person.setAge(cursor.getString(4));
            person.setSex(cursor.getString(5));
        }
        return person;
    }
}
