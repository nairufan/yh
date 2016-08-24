package com.jl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by fannairu on 2016/6/25.
 */
@Entity
@Table(name="tb_order_items")
public class OrderItemEntity extends BaseEntity{
    private long orderId;
    private long goodsId;
    private int amount;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
