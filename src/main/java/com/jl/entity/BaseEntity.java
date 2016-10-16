package com.jl.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by fannairu on 2016/6/25.
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    protected long id;
    protected long userId;
    protected int existStatus;
    protected Timestamp createTime;
    protected Timestamp updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
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
