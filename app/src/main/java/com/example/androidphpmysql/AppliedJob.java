package com.example.androidphpmysql;

public class AppliedJob {

    private String title;
    private String status;
    private String jid;

    public AppliedJob(String title, String status,String jid) {

        this.title = title;
        this.jid=jid;
        this.status = status;
        }
        public String getJid(){return  jid;}
    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}
