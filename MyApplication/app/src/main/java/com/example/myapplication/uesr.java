package com.example.myapplication;

public class uesr {
    private int id;
    private String name;
    private String name1;
    private String pass;
    private String edg;
    private  String sex;
    public uesr(){};
    public uesr(String name,String name1,String pass,String edg,String sex){
        this.name=name;
        this.name1=name1;
        this.pass=pass;
        this.edg=edg;
        this.sex=sex;

    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName1(){
        return name1;
    }
    public void setName1(String name1){
        this.name1=name1;
    }
    public String getpass(){
        return pass;
    }
    public void setpass(String pass){
        this.pass=pass;
    }
    public String getedg(){
        return edg;
    }
    public void setedg(String edg){
        this.edg=edg;
    }
    public String getsex(){
        return sex;
    }
    public void setsex(String sex){
        this.sex=sex;
    }

}
