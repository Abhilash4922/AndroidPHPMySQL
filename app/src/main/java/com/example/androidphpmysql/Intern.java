package com.example.androidphpmysql;

public class Intern {
    private String ititle;
    private String idescription;
    private String ilink;

    public Intern(String titleIntern, String descriptionIntern, String linkIntern) {
        this.ititle = titleIntern;
        this.idescription = descriptionIntern;
        this.ilink = linkIntern;
    }

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
