package com.example.androidphpmysql;

public class Intern {
    private String Interntitle;
    private String Interndescription;
    private String Internlink;

    public Intern(String Interntitle, String Interndescription, String Internlink) {
        this.Interntitle = Interntitle;
        this.Interndescription = Interndescription;
        this.Internlink = Internlink;
    }

    public String getInternTitle() {
        return Interntitle;
    }

    public String getInternDescription() {
        return Interndescription;
    }

    public String getInternLink() {
        return Internlink;
    }
}
