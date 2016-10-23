package com.jl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by fannairu on 2016/6/27.
 */
public abstract class BaseModel {
    protected String id;
    @JsonProperty(value = "create_time")
    protected long createTime;
    @JsonProperty(value = "update_time")
    protected long updateTime;
    @JsonProperty(value = "exist_status")
    protected int existStatus;

    public String getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id.toString();
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime.getTime();
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime.getTime();
    }

    public int getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(int existStatus) {
        this.existStatus = existStatus;
    }
}
