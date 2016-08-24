package com.jl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

/**
 * Created by fannairu on 2016/6/27.
 */
public abstract class BaseModel {
    protected long id;
    @JsonProperty(value = "create_time")
    protected long createTime;
    @JsonProperty(value = "update_time")
    protected long updateTime;
    @JsonProperty(value = "exist_status")
    protected int existStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime.getTime();
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime.getTime();
    }

    public int getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(int existStatus) {
        this.existStatus = existStatus;
    }
}
