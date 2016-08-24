package com.jl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by fannairu on 2016/6/25.
 */
@Entity
@Table(name="tb_goods")
public class GoodsEntity extends BaseEntity {
    private String productName;
    private String pics;
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
