package com.example.myapplication;

public class item {
//    private int image_id;
    private String description;
    private String title;

//    public item(int image_id, String description, String title) {
    public item(String description, String title) {
//        this.image_id = image_id;
        this.description = description;
        this.title = title;
    }
//
//    public void setImage_id(int image_id) {
//        this.image_id = image_id;
//    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public int getImage_id() {
//        return image_id;
//    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
