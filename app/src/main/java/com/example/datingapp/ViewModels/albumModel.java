package com.example.datingapp.ViewModels;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;

public class albumModel {
 private int userId, id;
 private String title;
 @SerializedName("body")
 private String text;


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
