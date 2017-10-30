package com.bwei.asus.geting20170922;

import java.util.List;

/**
 * Created by ASUS on 2017/9/22.
 */

public class SupperClass {

    private List<Newslist> newslist;

    public SupperClass(List<Newslist> newslist) {
        this.newslist = newslist;
    }

    @Override
    public String toString() {
        return "SupperClass{" +
                "newslist=" + newslist +
                '}';
    }

    public List<Newslist> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<Newslist> newslist) {
        this.newslist = newslist;
    }
}
