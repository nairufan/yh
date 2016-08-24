package com.jl.model;

import java.sql.Date;

/**
 * Created by fannairu on 2016/6/27.
 */
public class CategoryModel extends BaseModel {
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
