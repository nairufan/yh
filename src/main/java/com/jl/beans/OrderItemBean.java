package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by fannairu on 2016/7/2.
 */
public class OrderItemBean extends BaseBean{
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
