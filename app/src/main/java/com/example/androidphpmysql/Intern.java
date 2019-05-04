package com.example.androidphpmysql;

public class Intern {
    private String titleIntern;
    private String descriptionIntern;
    private String linkIntern;

    public Intern(String titleIntern, String descriptionIntern, String linkIntern) {
        this.titleIntern = titleIntern;
        this.descriptionIntern = descriptionIntern;
        this.linkIntern = linkIntern;
    }

    public String getTitleIntern() {
        return titleIntern;
    }

    public String getDescriptionIntern() {
        return descriptionIntern;
    }

    public String getLinkIntern() {
        return linkIntern;
    }
}
