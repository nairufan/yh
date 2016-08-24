package com.jl.controller;

import com.jl.beans.OrderBean;
import com.jl.beans.OrderItemBean;
import com.jl.entity.GoodsEntity;
import com.jl.entity.OrderEntity;
import com.jl.entity.OrderItemEntity;
import com.jl.model.OrderModel;
import com.jl.model.assembles.GoodsAssemble;
import com.jl.model.assembles.OrderAssemble;
import com.jl.model.assembles.OrderItemAssemble;
import com.jl.service.GoodsService;
import com.jl.service.OrderItemService;
import com.jl.service.OrderService;
import com.jl.utils.Constants;
import com.jl.utils.KdniaoTrackQueryAPI;
import com.jl.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.util.*;

/**
 * Created by fannairu on 2016/6/29.
 */
@Component
@Path("order")
@Produces("application/json")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderAssemble orderAssemble;
    @Autowired
    private OrderItemAssemble orderItemAssemble;
    @Autowired
    private Md5 md5;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsAssemble goodsAssemble;
    @Autowired
    private KdniaoTrackQueryAPI kdniaoTrackQueryAPI;

    @POST
    @Transactional
    public Map create(OrderBean orderBean) {
        Map reMap = new HashMap<String, Object>();
        if (orderBean.getId() == 0) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_ID_OR_NAME_REQUIRED);
            return reMap;
        }

        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        OrderEntity entity = orderService.findOne(orderBean.getId());
        if (entity == null) {
            entity = new OrderEntity();
            List<OrderItemEntity> orderItemEntities = new ArrayList<OrderItemEntity>();
            for (OrderItemBean orderItemBean : orderBean.getItems()) {
                OrderItemEntity orderItemEntity = new OrderItemEntity();
                orderItemEntity.setId(orderItemBean.getId());
                orderItemEntity.setCreateTime(orderItemBean.getCreateTime());
                orderItemEntity.setUpdateTime(orderItemBean.getUpdateTime());
                orderItemEntity.setUserId(userId);
                orderItemEntity.setGoodsId(orderItemBean.getGoodsId());
                orderItemEntity.setOrderId(orderItemBean.getOrderId());
                orderItemEntity.setAmount(orderItemBean.getAmount());
                orderItemEntity.setExistStatus(orderItemBean.getExistStatus());
                orderItemEntities.add(orderItemEntity);
            }
            orderItemService.save(orderItemEntities);
        }
        entity.setId(orderBean.getId());
        entity.setUserId(userId);
        entity.setCustomerId(orderBean.getCustomerId());
        entity.setMemo(orderBean.getMemo());
        entity.setExpressNumber(orderBean.getExpressNumber());
        entity.setExpressName(orderBean.getExpressName());
        entity.setCreateTime(orderBean.getCreateTime());
        entity.setUpdateTime(orderBean.getUpdateTime());
        entity.setStatus(orderBean.getStatus());
        entity.setExistStatus(orderBean.getExistStatus());
        entity.setCustomerName(orderBean.getCustomerName());
        entity.setCustomerTel(orderBean.getCustomerTel());
        entity.setCustomerAddress(orderBean.getCustomerAddress());
        entity = orderService.save(entity);
        reMap.put(Constants.RESULT, orderAssemble.assembleOrderModel(entity));
        return reMap;
    }

    @POST
    @Path("delete/{id}")
    public Map delete(@PathParam("id") long id) {
        Map reMap = new HashMap<String, Object>();
        OrderEntity entity = orderService.findOne(id);
        if (entity == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        entity.setExistStatus(Constants.DELETE);
        reMap.put(Constants.RESULT, Constants.SUCCESS);
        return reMap;
    }

    @POST
    @Path("update")
    public Map updateStatus(OrderBean orderBean) {
        Map reMap = new HashMap<String, Object>();
        if (orderBean.getId() == 0) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_ID_OR_NAME_REQUIRED);
            return reMap;
        }
        OrderEntity orderEntity = orderService.findOne(orderBean.getId());
        if (orderBean.getStatus() != 0) {
            orderEntity.setStatus(orderBean.getStatus());
        }
        if (orderBean.getExpressName() != null) {
            orderEntity.setExpressName(orderBean.getExpressName());
        }
        if (orderBean.getExpressNumber() != null) {
            orderEntity.setExpressNumber(orderBean.getExpressNumber());
        }
        orderEntity = orderService.save(orderEntity);
        reMap.put(Constants.RESULT, orderAssemble.assembleOrderModel(orderEntity));
        return reMap;
    }

    @GET
    @Path("detail")
    public Map getOrderDetail(@QueryParam("orderId") long orderId, @QueryParam("sig") String sig) {
        Map reMap = new HashMap<String, Object>();
        OrderEntity orderEntity = orderService.findOne(orderId);
        if (orderEntity != null) {
//            String encrypt = md5.getMd5Str(orderId + orderEntity.getCustomerTel());
//            if (!encrypt.equals(sig)) {
//                reMap.put(Constants.RESULT, Constants.ERROR_SIG);
//                return reMap;
//            }
            Iterable<OrderItemEntity> orderItemEntityIterable = orderItemService.findByOrderId(orderId);
            List<Long> goodsIds = new ArrayList<Long>();
            for (OrderItemEntity orderItemEntity : orderItemEntityIterable) {
                goodsIds.add(orderItemEntity.getGoodsId());
            }
            if (goodsIds.size() > 0) {
                Iterable<GoodsEntity> goodsEntityIterable = goodsService.findAll(goodsIds);
                reMap.put("goods", goodsAssemble.assembleGoodsModelList(goodsEntityIterable));
            }
            reMap.put("order", orderAssemble.assembleOrderModel(orderEntity));
            if (orderEntity.getExpressNumber() != null) {
                try {
                    reMap.put("express", kdniaoTrackQueryAPI.getOrderTracesByJson(orderEntity.getExpressName(), orderEntity.getExpressNumber()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return reMap;
    }

    @GET
    @Path("list")
    public Map getOrderList(@QueryParam("userId") Long userId,@QueryParam("start") int start, @QueryParam("size") int size,
                            @QueryParam("sort") String sort) {
        Map reMap = new HashMap();
        if (sort == null || "".equals(sort)) {
            sort = "DESC";
        }
        Sort sortAble = new Sort(Collections.singletonList(new Sort.Order(Sort.Direction.fromString(sort), "createTime")));
        Pageable pageable = new PageRequest(start, size, sortAble);
        Page<OrderEntity> orderEntities;
        if (userId != null) {
            orderEntities = orderService.findByUserId(userId, pageable);
        } else {
            orderEntities = orderService.findAll(pageable);
        }
        List<OrderModel> orderModels = orderAssemble.assembleOrderModelList(orderEntities);
        reMap.put("orderList", orderModels);
        reMap.put("totalPages", orderEntities.getTotalPages());
        return reMap;
    }
}
