package com.jl.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

/**
 * Created by fannairu on 2016/6/25.
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    protected long id;
    protected long userId;
    protected int existStatus;
    protected Date createTime;
    protected Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(int existStatus) {
        this.existStatus = existStatus;
    }
}
