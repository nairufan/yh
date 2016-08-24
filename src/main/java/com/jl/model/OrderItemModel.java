package com.jl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fannairu on 2016/6/29.
 */
public class OrderItemModel extends BaseModel {
    @JsonProperty(value = "order_id")
    private long orderId;
    @JsonProperty(value = "goods_id")
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
