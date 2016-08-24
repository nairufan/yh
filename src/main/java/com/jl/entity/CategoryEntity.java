package com.jl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by fannairu on 2016/6/25.
 */
@Entity
@Table(name = "tb_category")
public class CategoryEntity extends BaseEntity {

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
}
