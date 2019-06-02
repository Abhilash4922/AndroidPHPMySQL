package com.example.androidphpmysql;

public class AppliedIntern {

    private String intern_title;
    private String intern_status;
    private String iid;
    private String cid;

    public AppliedIntern(String intern_title, String intern_status,String iid,String cid) {
this.iid=iid;
        this.cid=cid;
        this.intern_title = intern_title;
        this.intern_status = intern_status;
        }
    public String getCid() {
        return cid;
    }

    public String getInternTitle() {
        return intern_title;
    }

    public String getIid() {
        return iid;
    }

    public String getInternStatus() {
        return intern_status;
    }
}
