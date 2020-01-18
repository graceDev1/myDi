package com.example.codler_fun.mycoachdbt.model;

public class User
{
   private String useid;

    private String username;
    private String imageUrl;

    public User() {
    }
    public User(String useid, String username, String imageUrl) {
        this.useid = useid;
        this.username = username;
        this.imageUrl = imageUrl;
    }


    public String getUseid() {
        return useid;
    }

    public void setUseid(String useid) {
        this.useid = useid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
