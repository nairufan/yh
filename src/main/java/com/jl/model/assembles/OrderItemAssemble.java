package com.jl.model.assembles;

import com.jl.entity.OrderItemEntity;
import com.jl.model.OrderItemModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fannairu on 2016/6/29.
 */
@Component
public class OrderItemAssemble {
    public OrderItemModel assembleOrderItemModel(OrderItemEntity entity) {
        OrderItemModel model = new OrderItemModel();
        model.setId(entity.getId());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        model.setAmount(entity.getAmount());
        model.setGoodsId(entity.getGoodsId());
        model.setOrderId(entity.getOrderId());
        return model;
    }

    public List<OrderItemModel> assembleOrderItemModels(Iterable<OrderItemEntity> orderItemEntities) {
        if (orderItemEntities == null) {
            return null;
        }
        List<OrderItemModel> modelList = new ArrayList<OrderItemModel>();
        for (OrderItemEntity orderItemEntity : orderItemEntities) {
            modelList.add(assembleOrderItemModel(orderItemEntity));
        }
        return modelList;
    }

    public Map<Long, List<OrderItemModel>> assembleOrderItemModelMap(Iterable<OrderItemEntity> orderItemEntities) {
        if (orderItemEntities == null) {
            return null;
        }
        Map<Long, List<OrderItemModel>> orderItemModelMap = new HashMap<Long, List<OrderItemModel>>();
        for (OrderItemEntity orderItemEntity : orderItemEntities) {
            List<OrderItemModel> orderItemModels = orderItemModelMap.get(orderItemEntity.getOrderId());
            if (orderItemModels == null) {
                orderItemModels = new ArrayList<OrderItemModel>();
            }
            orderItemModels.add(assembleOrderItemModel(orderItemEntity));
            orderItemModelMap.put(orderItemEntity.getOrderId(), orderItemModels);
        }
        return orderItemModelMap;
    }

}
