package com.example.myapplication;

public class history_item {
    private String description;
    private String title;

    public history_item(String description, String title) {
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
