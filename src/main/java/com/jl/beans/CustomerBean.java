package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jl.entity.CustomerEntity;
import com.jl.utils.Constants;

/**
 * Created by fannairu on 2016/6/28.
 */
public class CustomerBean extends BaseBean {

    @JsonProperty(value = "customer_name")
    private String customerName;
    @JsonProperty(value = "customer_tel")
    private String customerTel;
    @JsonProperty(value = "customer_address")
    private String customerAddress;
    private String memo;
    private String pics;
    private int level;
    @JsonProperty(value = "is_default")
    private int isDefault;
    @JsonProperty(value = "customer_group")
    private String customerGroup;

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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public CustomerEntity toCustomerEntity(Long userId) {
        CustomerEntity entity = new CustomerEntity();
        entity.setUserId(userId);
        entity.setId(this.getId());
        entity.setMemo(this.getMemo());
        entity.setCustomerName(this.getCustomerName());
        entity.setCustomerAddress(this.getCustomerAddress());
        entity.setCustomerTel(this.getCustomerTel());
        entity.setCreateTime(this.getCreateTime());
        entity.setUpdateTime(this.getUpdateTime());
        entity.setPics(this.getPics());
        entity.setExistStatus(this.getExistStatus());
        entity.setLevel(this.getLevel());
        entity.setCustomerGroup(this.getCustomerGroup());
        entity.setIsDefault(this.getIsDefault());
        return entity;
    }
}
