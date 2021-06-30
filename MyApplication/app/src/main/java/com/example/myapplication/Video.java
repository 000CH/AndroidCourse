package com.example.myapplication;

import java.io.Serializable;

public class Video implements Serializable {
    private static final long serialVersionUID=1L;
    private int id;
    private String name;
    private String kind;
    private String video;
    private String cover;
    private String introdution;
    private int uploaderID;
    private String time;
    private String empirical_value = "";
    private String study_time = "";
    private String visit_time = "";
    public Video(){ }
    public Video(int id,String name,String kind,String video,String cover,String introdution,int uploaderID,String time){
        this.id=id;
        this.name=name;
        this.kind=kind;
        this.video=video;
        this.cover=cover;
        this.introdution=introdution;
        this.uploaderID=uploaderID;
        this.time=time;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setVideo(String video){
        this.video=video;
    }
    public void setKind(String kind){
        this.kind=kind;
    }
    public void setCover(String cover){
        this.cover=cover;
    }
    public void setIntrodution(String introdution){
        this.introdution=introdution;
    }
    public void setUploaderID(int uploaderID){
        this.uploaderID=uploaderID;
    }
    public void setTime(String time){
        this.time=time;
    }
    public void setEmpirical_value(String value){this.empirical_value=value;}
    public void setStudy_time(String study_time){this.study_time=study_time;}
    public void setVisit_time(String visit_time){this.visit_time=visit_time;}
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getKind(){
        return this.kind;
    }
    public String getVideo(){
        return this.video;
    }
    public String getCover(){
        return this.cover;
    }
    public String getIntrodution(){
        return this.introdution;
    }
    public int getUploaderID(){
        return this.uploaderID;
    }
    public String getTime(){
        return this.time;
    }
    public String getEmpirical_value(){return this.empirical_value;}
    public String getStudy_time(){return this.study_time;}
    public String getVisit_time(){return this.visit_time;}
}
