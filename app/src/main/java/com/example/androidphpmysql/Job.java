package com.example.androidphpmysql;

public class Job {

    private String title;
    private String description;
    private String link;
    private int id;
    private int user_id;

    public Job(String title, String description, String link,int id,int user_id) {

        this.title = title;
        this.description = description;
        this.link = link;
        this.id=id;
        this.user_id=user_id;
    }
    public int getId(){
        return id;
    }

    public int getUser_id(){
        return user_id;
    }

    public String getJobTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
