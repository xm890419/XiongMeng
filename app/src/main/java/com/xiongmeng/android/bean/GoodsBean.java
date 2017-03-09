package com.xiongmeng.android.bean;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class GoodsBean {
    /**
     * cover_price : 159.00
     * figure : /1477984921265.jpg
     * name : 现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪
     * product_id : 9356
     */

    private String cover_price;
    private String figure;
    private String name;
    private String product_id;
    private String origin_price;
    private boolean isChecked = true;
    private String seller = "尚硅谷";

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(String origin_price) {
        this.origin_price = origin_price;
    }

    //某个商品在购物车购买的数量
    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                ", number=" + number +
                '}';
    }
}
