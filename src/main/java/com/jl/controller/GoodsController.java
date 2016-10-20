package com.jl.controller;

import com.jl.beans.GoodsBean;
import com.jl.entity.CategoryEntity;
import com.jl.entity.GoodsEntity;
import com.jl.model.GoodsModel;
import com.jl.model.assembles.CategoryAssemble;
import com.jl.model.assembles.GoodsAssemble;
import com.jl.service.CategoryService;
import com.jl.service.GoodsService;
import com.jl.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.util.*;

/**
 * Created by fannairu on 2016/6/29.
 */
@Component
@Path("goods")
@Produces("application/json")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsAssemble goodsAssemble;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryAssemble categoryAssemble;
    @Autowired
    private HttpSession session;

    @POST
    public Map create(GoodsBean goodsBean) {
        Map reMap = new HashMap<String, Object>();
        if (goodsBean.getId() == 0 || goodsBean.getProductName() == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_ID_OR_NAME_REQUIRED);
            return reMap;
        }
        GoodsEntity entity = goodsBean.toGoodsEntity((Long) session.getAttribute(Constants.USER_ID));
        entity = goodsService.save(entity);
        reMap.put(Constants.RESULT, goodsAssemble.assembleGoodsModel(entity));

        return reMap;
    }

    @POST
    @Path("delete/{id}")
    public Map delete(@PathParam("id") long id) {
        Map reMap = new HashMap<String, Object>();
        GoodsEntity entity = goodsService.findOne(id);
        if (entity == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        entity.setExistStatus(Constants.DELETE);
        goodsService.save(entity);
        reMap.put(Constants.RESULT, Constants.SUCCESS);
        return reMap;
    }

    @POST
    @Path("update")
    public Map update(GoodsBean goodsBean) {
        Map reMap = new HashMap<String, Object>();
        GoodsEntity entity = goodsService.findOne(goodsBean.getId());
        if (entity == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        String productName = goodsBean.getProductName();
        long categoryId = goodsBean.getCategoryId();
        String memo = goodsBean.getMemo();
        String pics = goodsBean.getPics();
        int inventory = goodsBean.getInventory();
        if (productName != null) {
            entity.setProductName(productName);
        }
        if (categoryId != 0) {
            entity.setCategoryId(categoryId);
        }
        if (memo != null) {
            entity.setMemo(memo);
        }
        if (pics != null) {
            entity.setPics(pics);
        }
        if (inventory != 0) {
            entity.setInventory(inventory);
        }
        entity = goodsService.save(entity);
        reMap.put(Constants.RESULT, goodsAssemble.assembleGoodsModel(entity));

        return reMap;
    }

    @GET
    @Path("{id}")
    public Map getGood(@PathParam("id") long id) {
        Map reMap = new HashMap();
        GoodsEntity entity = goodsService.findOne(id);
        reMap.put(Constants.RESULT, goodsAssemble.assembleGoodsModel(entity));
        return reMap;
    }

    @GET
    @Path("list")
    public Map getGoodsList(@QueryParam("userId") Long userId,
                            @QueryParam("categoryId") Long categoryId,
                            @QueryParam("start") int start,
                            @QueryParam("size") int size,
                            @QueryParam("sort") String sort) {
        Map reMap = new HashMap();
        if (sort == null || "".equals(sort)) {
            sort = "DESC";
        }
        Sort sortAble = new Sort(Collections.singletonList(new Sort.Order(Sort.Direction.fromString(sort), "createTime")));
        Pageable pageable = new PageRequest(start, size, sortAble);
        Page<GoodsEntity> goodsEntities;
        if (userId != null) {
            if (categoryId != null) {
                goodsEntities = goodsService.findByUserIdAndCategoryId(userId, categoryId, pageable);
            } else {
                goodsEntities = goodsService.findByUserId(userId, pageable);
            }
        } else {
            goodsEntities = goodsService.findAll(pageable);
        }
        List<GoodsModel> goodsModels = goodsAssemble.assembleGoodsModelList(goodsEntities);
        List<Long> categoryIds = new LinkedList<Long>();
        for (GoodsModel goodsModel : goodsModels) {
            categoryIds.add(goodsModel.getCategoryId());
        }
        Iterable<CategoryEntity> categoryEntities = categoryService.findAll(categoryIds);
        reMap.put("goodsList", goodsModels);
        reMap.put("categoryMap", categoryAssemble.assembleCategoryModelMap(categoryEntities));
        reMap.put("totalPages", goodsEntities.getTotalPages());
        return reMap;
    }

}
