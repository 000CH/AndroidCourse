package com.example.myapplication;

public class Person {
    private int userID;
    private String name;
    private String nickname;
    private String head;
    private String age;
    private String sex;
    public void setUserID(int userID){
        this.userID =userID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public void setHead(String head){
        this.head = head;
    }
    public void setAge(String age){
        this.age = age;
    }
    public void setSex(String sex){
        this.sex =sex;
    }
    public int getUserID(){
        return userID;
    }
    public String getName(){
        return name;
    }
    public String getNickname(){
        return nickname;
    }
    public String getHead(){
        return head;
    }
    public String getAge(){
        return age;
    }
    public String getSex(){
        return sex;
    }

}
