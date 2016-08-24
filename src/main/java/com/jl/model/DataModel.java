package com.jl.model;

import java.util.List;

/**
 * Created by fannairu on 2016/7/3.
 */
public class DataModel {
    private Boolean sync;
    private UserModel userModel;
    private List<CategoryModel> categoryModelList;
    private List<CustomerModel> customerModelList;
    private List<GoodsModel> goodsModelList;
    private List<OrderModel> orderModelList;
    private List<OrderItemModel> orderItemModelList;
    private List<SettingsModel> settingsModelList;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public List<CategoryModel> getCategoryModelList() {
        return categoryModelList;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public List<CustomerModel> getCustomerModelList() {
        return customerModelList;
    }

    public void setCustomerModelList(List<CustomerModel> customerModelList) {
        this.customerModelList = customerModelList;
    }

    public List<GoodsModel> getGoodsModelList() {
        return goodsModelList;
    }

    public void setGoodsModelList(List<GoodsModel> goodsModelList) {
        this.goodsModelList = goodsModelList;
    }

    public List<OrderModel> getOrderModelList() {
        return orderModelList;
    }

    public void setOrderModelList(List<OrderModel> orderModelList) {
        this.orderModelList = orderModelList;
    }

    public List<OrderItemModel> getOrderItemModelList() {
        return orderItemModelList;
    }

    public void setOrderItemModelList(List<OrderItemModel> orderItemModelList) {
        this.orderItemModelList = orderItemModelList;
    }

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    public List<SettingsModel> getSettingsModelList() {
        return settingsModelList;
    }

    public void setSettingsModelList(List<SettingsModel> settingsModelList) {
        this.settingsModelList = settingsModelList;
    }
}
