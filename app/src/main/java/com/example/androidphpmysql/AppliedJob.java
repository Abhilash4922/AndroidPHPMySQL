package com.example.androidphpmysql;

public class AppliedJob {

    private String title;
    private String status;

    public AppliedJob(String title, String status) {

        this.title = title;
        this.status = status;
        }
    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}
