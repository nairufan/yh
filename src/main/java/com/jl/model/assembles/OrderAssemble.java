package com.jl.model.assembles;

import com.jl.entity.OrderEntity;
import com.jl.model.OrderModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fannairu on 2016/6/29.
 */
@Component
public class OrderAssemble {
    public OrderModel assembleOrderModel(OrderEntity entity) {
        OrderModel model = new OrderModel();
        model.setId(entity.getId());
        model.setMemo(entity.getMemo());
        model.setCustomerId(entity.getCustomerId());
        model.setUpdateTime(entity.getUpdateTime());
        model.setCreateTime(entity.getCreateTime());
        model.setExpressName(entity.getExpressName());
        model.setExpressNumber(entity.getExpressNumber());
        model.setStatus(entity.getStatus());
        model.setCustomerName(entity.getCustomerName());
        model.setCustomerTel(entity.getCustomerTel());
        model.setCustomerAddress(entity.getCustomerAddress());
        model.setExistStatus(entity.getExistStatus());
        return model;
    }

    public List<OrderModel> assembleOrderModelList(Iterable<OrderEntity> orderEntityIterable) {
        if (orderEntityIterable == null) {
            return null;
        }
        List<OrderModel> orderModelList = new ArrayList<OrderModel>();
        for (OrderEntity entity : orderEntityIterable) {
            orderModelList.add(assembleOrderModel(entity));
        }
        return orderModelList;
    }
}
