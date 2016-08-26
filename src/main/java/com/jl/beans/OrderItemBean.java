package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jl.entity.OrderItemEntity;

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

    public OrderItemEntity toOrderItemEntity(Long userId) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setId(this.getId());
        orderItemEntity.setCreateTime(this.getCreateTime());
        orderItemEntity.setUpdateTime(this.getUpdateTime());
        orderItemEntity.setUserId(userId);
        orderItemEntity.setGoodsId(this.getGoodsId());
        orderItemEntity.setOrderId(this.getOrderId());
        orderItemEntity.setAmount(this.getAmount());
        orderItemEntity.setExistStatus(this.getExistStatus());
        return  orderItemEntity;
    }
}
