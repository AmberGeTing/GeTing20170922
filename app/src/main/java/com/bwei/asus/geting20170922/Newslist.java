package com.bwei.asus.geting20170922;

/**
 * Created by ASUS on 2017/9/22.
 */
public class Newslist {

    private String description;
    private String picUrl;

    public Newslist(String description, String picUrl) {
        this.description = description;
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "Newslist{" +
                "description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
