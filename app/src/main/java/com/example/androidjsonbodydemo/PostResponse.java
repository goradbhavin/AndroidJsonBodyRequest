package com.example.androidjsonbodydemo;

import com.google.gson.annotations.SerializedName;

public class PostResponse {

    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;
    @SerializedName("userId")
    private String userId;
    @SerializedName("id")
    private Integer id;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return body;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUserId(){
        return userId;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return id;
    }
}
