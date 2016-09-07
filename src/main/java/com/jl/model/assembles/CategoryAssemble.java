package com.jl.model.assembles;

import com.jl.entity.CategoryEntity;
import com.jl.model.CategoryModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by fannairu on 2016/6/27.
 */
@Component
public class CategoryAssemble {
    public CategoryModel assembleCategoryModel(CategoryEntity entity) {
        CategoryModel model = new CategoryModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setMemo(entity.getMemo());
        model.setCreateTime(entity.getCreateTime());
        model.setUpdateTime(entity.getUpdateTime());
        model.setExistStatus(entity.getExistStatus());
        return model;
    }

    public List<CategoryModel> assembleCategoryModelList(Iterable<CategoryEntity> categoryEntityIterable) {
        if (categoryEntityIterable == null) {
            return null;
        }
        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        for (CategoryEntity entity : categoryEntityIterable) {
            categoryModelList.add(assembleCategoryModel(entity));
        }
        return categoryModelList;
    }

    public Map<Long, CategoryModel> assembleCategoryModelMap(Iterable<CategoryEntity> categoryEntityIterable) {
        if (categoryEntityIterable == null) {
            return null;
        }
        Map<Long, CategoryModel> categoryModelMap = new Hashtable<Long, CategoryModel>();
        for (CategoryEntity entity : categoryEntityIterable) {
            categoryModelMap.put(entity.getId(), assembleCategoryModel(entity));
        }
        return categoryModelMap;
    }

}
