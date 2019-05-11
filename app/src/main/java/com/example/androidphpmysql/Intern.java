package com.example.androidphpmysql;

public class Intern {
    private String ititle;
    private String idescription;
    private String ilink;
    private int user_id;
    private int id;

    public Intern(String titleIntern, String descriptionIntern, String linkIntern,int user_id,int id) {
        this.ititle = titleIntern;
        this.user_id=user_id;
        this.id=id;
        this.idescription = descriptionIntern;
        this.ilink = linkIntern;
    }
    public int getId(){return id;}
public int getUser_id(){return user_id;}
    public String getiTitle() {
        return ititle;
    }

    public String getIdescription() {
        return idescription;
    }

    public String getiLink() {
        return ilink;
    }
}
