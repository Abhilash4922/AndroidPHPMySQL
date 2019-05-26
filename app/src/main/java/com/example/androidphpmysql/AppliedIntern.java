package com.example.androidphpmysql;

public class AppliedIntern {

    private String intern_title;
    private String intern_status;

    public AppliedIntern(String intern_title, String intern_status) {

        this.intern_title = intern_title;
        this.intern_status = intern_status;
        }
    public String getInternTitle() {
        return intern_title;
    }

    public String getInternStatus() {
        return intern_status;
    }
}
