package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fannairu on 2016/6/29.
 */
public class GoodsBean extends BaseBean{
    @JsonProperty(value = "product_name")
    private String productName;
    private String pics;
    @JsonProperty(value = "category_id")
    private long categoryId;
    private int inventory;
    private String memo;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
