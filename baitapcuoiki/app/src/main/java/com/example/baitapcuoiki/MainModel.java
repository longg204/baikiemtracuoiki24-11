package com.example.baitapcuoiki;

public class MainModel {
    private String name, singer, turl;

    MainModel() {

    }

    public MainModel(String name, String singer, String turl) {
        this.name = name;
        this.singer = singer;
        this.turl = turl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }
}

