package com.jl.controller;

import com.jl.beans.CustomerBean;
import com.jl.entity.CategoryEntity;
import com.jl.entity.CustomerEntity;
import com.jl.model.CustomerModel;
import com.jl.model.assembles.CustomerAssemble;
import com.jl.service.CustomerService;
import com.jl.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fannairu on 2016/6/28.
 */
@Path("customer")
@Produces("application/json")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerAssemble customerAssemble;
    @Autowired
    private HttpSession session;

    @POST
    public Map create(CustomerBean customer) {
        Map reMap = new HashMap<String, Object>();
        if (customer.getId() == 0 || customer.getCustomerName() == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_ID_OR_NAME_REQUIRED);
            return reMap;
        }
        CustomerEntity entity = customer.toCustomerEntity((Long) session.getAttribute(Constants.USER_ID));
        entity = customerService.save(entity);
        reMap.put(Constants.RESULT, customerAssemble.assembleCustomerModel(entity));
        return reMap;
    }

    @POST
    @Path("delete/{id}")
    public Map delete(@PathParam("id") long id) {
        Map reMap = new HashMap<String, Object>();
        CustomerEntity entity = customerService.findOne(id);
        if (entity == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        entity.setExistStatus(Constants.DELETE);
        customerService.save(entity);
        reMap.put(Constants.RESULT, Constants.SUCCESS);
        return reMap;
    }

    @POST
    @Path("update")
    public Map update(CustomerBean customer) {
        Map reMap = new HashMap<String, Object>();
        CustomerEntity entity = customerService.findOne(customer.getId());
        if (entity == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        String customerName = customer.getCustomerName();
        String customerAddress = customer.getCustomerAddress();
        String customerTel = customer.getCustomerTel();
        String memo = customer.getMemo();
        String pics = customer.getPics();
        int level = customer.getLevel();

        if (customerName != null) {
            entity.setCustomerName(customerName);
        }
        if (customerAddress != null) {
            entity.setCustomerAddress(customerAddress);
        }
        if (customerTel != null) {
            entity.setCustomerTel(customerTel);
        }
        if (memo != null) {
            entity.setMemo(memo);
        }
        if (pics != null) {
            entity.setPics(pics);
        }
        if (level != 0) {
            entity.setLevel(level);
        }
        entity = customerService.save(entity);
        reMap.put(Constants.RESULT, customerAssemble.assembleCustomerModel(entity));
        return reMap;
    }

    @GET
    @Path("list")
    public Map getCustomerList(@QueryParam("userId") Long userId, @QueryParam("start") int start, @QueryParam("size") int size,
                               @QueryParam("sort") String sort) {
        Map reMap = new HashMap();
        if (sort == null || "".equals(sort)) {
            sort = "DESC";
        }
        Sort sortAble = new Sort(Collections.singletonList(new Sort.Order(Sort.Direction.fromString(sort), "createTime")));
        Pageable pageable = new PageRequest(start, size, sortAble);
        Page<CustomerEntity> customerEntities;
        if (userId != null) {
            customerEntities = customerService.findByUserId(userId, pageable);
        } else {
            customerEntities = customerService.findAll(pageable);
        }
        List<CustomerModel> customerModels = customerAssemble.assembleCustomerModelList(customerEntities);
        reMap.put("customerList", customerModels);
        reMap.put("totalPages", customerEntities.getTotalPages());
        return reMap;
    }

    @GET
    @Path("{id}")
    public Map getCustomer(@PathParam("id") long id) {
        Map reMap = new HashMap<String, Object>();
        CustomerEntity entity = customerService.findOne(id);
        reMap.put(Constants.RESULT, customerAssemble.assembleCustomerModel(entity));
        return reMap;
    }

}
