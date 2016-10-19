package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jl.entity.OrderEntity;
import com.jl.entity.UserEntity;

/**
 * Created by fannairu on 2016/7/2.
 */
public class OrderBean extends BaseBean {
    @JsonProperty(value = "customer_id")
    private long customerId;
    private int status;
    private String memo;
    @JsonProperty(value = "express_number")
    private String expressNumber;
    @JsonProperty(value = "express_name")
    private String expressName;
    private OrderItemBean[] items;
    @JsonProperty(value = "customer_name")
    private String customerName;
    @JsonProperty(value = "customer_tel")
    private String customerTel;
    @JsonProperty(value = "customer_address")
    private String customerAddress;
    private double price;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public OrderItemBean[] getItems() {
        return items;
    }

    public void setItems(OrderItemBean[] items) {
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderEntity toOrderEntity(Long userId) {
        OrderEntity entity = new OrderEntity();
        entity.setId(this.getId());
        entity.setUserId(userId);
        entity.setCustomerId(this.getCustomerId());
        entity.setMemo(this.getMemo());
        entity.setExpressNumber(this.getExpressNumber());
        entity.setExpressName(this.getExpressName());
        entity.setCreateTime(this.getCreateTime());
        entity.setUpdateTime(this.getUpdateTime());
        entity.setStatus(this.getStatus());
        entity.setExistStatus(this.getExistStatus());
        entity.setCustomerName(this.getCustomerName());
        entity.setCustomerTel(this.getCustomerTel());
        entity.setCustomerAddress(this.getCustomerAddress());
        entity.setPrice(this.getPrice());
        return entity;
    }
}
