package com.example.androidphpmysql;

public class AppliedJobMessage {

    private String message;
private String time;
    public AppliedJobMessage(String message,String time) {
this.time=time;
        this.message = message;
        }
        public String getMessage(){return message;}
    public String getTime(){return time;}

}
