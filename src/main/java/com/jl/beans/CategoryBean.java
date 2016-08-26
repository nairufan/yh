package com.jl.beans;

import com.jl.entity.CategoryEntity;
import com.jl.utils.Constants;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fannairu on 2016/6/27.
 */
@XmlRootElement
public class CategoryBean extends BaseBean {

    private String name;
    private String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public CategoryEntity toCategoryEntity(Long userId) {
        CategoryEntity entity = new CategoryEntity();
        entity.setUserId(userId);
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setMemo(this.getMemo());
        entity.setCreateTime(this.getCreateTime());
        entity.setUpdateTime(this.getUpdateTime());
        entity.setExistStatus(this.getExistStatus());
        return entity;
    }
}
