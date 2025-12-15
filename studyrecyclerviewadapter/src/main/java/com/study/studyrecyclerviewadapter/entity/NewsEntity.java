package com.study.studyrecyclerviewadapter.entity;

public class NewsEntity {

    private long id;
    private String title;
    private int coverRes;
    private String publishTime;
    private int readCount;

    public NewsEntity(long id, String title, int coverRes, String publishTime, int readCount) {
        this.id = id;
        this.title = title;
        this.coverRes = coverRes;
        this.publishTime = publishTime;
        this.readCount = readCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoverRes() {
        return coverRes;
    }

    public void setCoverRes(int coverRes) {
        this.coverRes = coverRes;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }
}
