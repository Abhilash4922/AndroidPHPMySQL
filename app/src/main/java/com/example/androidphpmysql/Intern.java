package com.example.androidphpmysql;

public class Intern {

    private String ititle;
    private String idescription;
    private String ilink;
    private int iid;
    private int user_id;

    public Intern(String ititle, String idescription, String ilink,int iid,int user_id) {

        this.ititle = ititle;
        this.idescription = idescription;
        this.ilink = ilink;
        this.iid=iid;
        this.user_id=user_id;
    }
    public int getIid(){
        return iid;
    }

    public int getUser_id(){
        return user_id;
    }

    public String getInternTitle() {
        return ititle;
    }

    public String getInternDescription() {
        return idescription;
    }

    public String getInternLink() {
        return ilink;
    }
}
