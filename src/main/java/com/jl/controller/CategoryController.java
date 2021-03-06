package com.jl.controller;

import com.jl.beans.CategoryBean;
import com.jl.entity.CategoryEntity;
import com.jl.model.CategoryModel;
import com.jl.model.assembles.CategoryAssemble;
import com.jl.service.CategoryService;
import com.jl.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fannairu on 2016/6/27.
 */
@Component
@Path("category")
@Produces("application/json")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryAssemble categoryAssemble;
    @Autowired
    private HttpSession session;

    @POST
    public Map create(CategoryBean category) {
        Map reMap = new HashMap<String, Object>();
        if (category.getId() == 0 || category.getName() == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_ID_OR_NAME_REQUIRED);
            return reMap;
        }
        CategoryEntity entity = category.toCategoryEntity((Long) session.getAttribute(Constants.USER_ID));
        entity = service.save(entity);
        reMap.put(Constants.RESULT, categoryAssemble.assembleCategoryModel(entity));
        return reMap;
    }


    @POST
    @Path("delete/{id}")
    public Map delete(@PathParam("id") long id) {
        Map reMap = new HashMap<String, Object>();
        CategoryEntity entity = service.findOne(id);
        if (entity == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        entity.setExistStatus(Constants.DELETE);
        service.save(entity);
        reMap.put(Constants.RESULT, Constants.SUCCESS);
        return reMap;
    }

    @POST
    @Path("update")
    public Map update(CategoryBean category) {
        Map reMap = new HashMap<String, Object>();
        CategoryEntity entity = service.findOne(category.getId());
        if (entity == null) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        String name = category.getName();
        String memo = category.getMemo();
        if (name != null) {
            entity.setName(name);
        }
        if (memo != null) {
            entity.setMemo(memo);
        }
        entity = service.save(entity);
        reMap.put(Constants.RESULT, categoryAssemble.assembleCategoryModel(entity));
        return reMap;
    }

    private Page<CategoryEntity> getList(Long userId, @QueryParam("start") int start, int size, String sort) {
        if (sort == null || "".equals(sort)) {
            sort = "DESC";
        }
        Sort sortAble = new Sort(Collections.singletonList(new Sort.Order(Sort.Direction.fromString(sort), "createTime")));
        Pageable pageable = new PageRequest(start, size, sortAble);
        Page<CategoryEntity> categoryEntities;
        if (userId != null) {
            categoryEntities = service.findByUserId(userId, pageable);
        } else {
            categoryEntities = service.findAll(pageable);
        }
        return categoryEntities;
    }

    @GET
    @Path("list")
    public Map getCategoryList(@QueryParam("userId") Long userId, @QueryParam("start") int start, @QueryParam("size") int size,
                               @QueryParam("sort") String sort) {
        Map reMap = new HashMap();
        Page<CategoryEntity> categoryEntities = getList(userId, start, size, sort);
        List<CategoryModel> categoryModels = categoryAssemble.assembleCategoryModelList(categoryEntities);
        reMap.put("categoryList", categoryModels);
        reMap.put("totalPages", categoryEntities.getTotalPages());
        return reMap;
    }

    @GET
    @Path("current-list")
    public Map getCurrentCategoryList(@QueryParam("start") int start, @QueryParam("size") int size,
                                      @QueryParam("sort") String sort) {
        Map reMap = new HashMap();
        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        Page<CategoryEntity> categoryEntities = getList(userId, start, size, sort);
        List<CategoryModel> categoryModels = categoryAssemble.assembleCategoryModelList(categoryEntities);
        reMap.put("categoryList", categoryModels);
        reMap.put("totalPages", categoryEntities.getTotalPages());
        return reMap;
    }

    @GET
    @Path("{id}")
    public Map getCategory(@PathParam("id") long id) {
        Map reMap = new HashMap<String, Object>();
        CategoryEntity entity = service.findOne(id);
        reMap.put(Constants.RESULT, categoryAssemble.assembleCategoryModel(entity));
        return reMap;
    }

}
