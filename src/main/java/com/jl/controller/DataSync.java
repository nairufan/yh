package com.jl.controller;

import com.jl.entity.*;
import com.jl.model.DataModel;
import com.jl.model.assembles.*;
import com.jl.service.*;
import com.jl.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.sql.Date;

/**
 * Created by fannairu on 2016/7/2.
 */
@Component
@Path("sync")
@Produces("application/json")
public class DataSync {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CategoryAssemble categoryAssemble;
    @Autowired
    private CustomerAssemble customerAssemble;
    @Autowired
    private GoodsAssemble goodsAssemble;
    @Autowired
    private OrderAssemble orderAssemble;
    @Autowired
    private OrderItemAssemble orderItemAssemble;
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private SettingsAssemble settingsAssemble;
    @Autowired
    private HttpSession session;

    @GET
    @Path("all")
    public DataModel getInitialData(
            @QueryParam("app_version") String appVersion,
            @QueryParam("system_type") String systemType,
            @QueryParam("system_version") String systemVersion,
            @QueryParam("device_id") @DefaultValue("-1") String deviceId,
            @QueryParam("device_mode") String deviceMode,
            @QueryParam("connection_mode") String connectionMode
    ) {
        DataModel dataModel = new DataModel();
        dataModel.setSync(false);
        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        Iterable<CategoryEntity> categoryEntityIterable = categoryService.findByUserId(userId);
        dataModel.setCategoryModelList(categoryAssemble.assembleCategoryModelList(categoryEntityIterable));

        Iterable<CustomerEntity> customerModelIterable = customerService.findByUserId(userId);
        dataModel.setCustomerModelList(customerAssemble.assembleCustomerModelList(customerModelIterable));

        Iterable<GoodsEntity> goodsEntityIterable = goodsService.findByUserId(userId);
        dataModel.setGoodsModelList(goodsAssemble.assembleGoodsModelList(goodsEntityIterable));

        Iterable<OrderEntity> orderServiceIterable = orderService.findByUserId(userId);
        dataModel.setOrderModelList(orderAssemble.assembleOrderModelList(orderServiceIterable));

        Iterable<OrderItemEntity> orderItemEntityIterable = orderItemService.findByUserId(userId);
        dataModel.setOrderItemModelList(orderItemAssemble.assembleOrderItemModels(orderItemEntityIterable));

        Iterable<SettingsEntity> settingsEntityIterable = settingsService.findByUserId(userId);
        dataModel.setSettingsModelList(settingsAssemble.assembleSettingsModelList(settingsEntityIterable));

        return dataModel;
    }

}
