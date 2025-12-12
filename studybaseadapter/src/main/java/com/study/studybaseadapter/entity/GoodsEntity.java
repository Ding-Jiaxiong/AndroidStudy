package com.study.studybaseadapter.entity;

public class GoodsEntity {

    private int imgRes;
    private String title;
    private double price;
    private Integer sales;
    private boolean isCollected;

    public GoodsEntity(int imgRes, String title, double price, Integer sales, boolean isCollected) {
        this.imgRes = imgRes;
        this.title = title;
        this.price = price;
        this.sales = sales;
        this.isCollected = isCollected;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}
