package com.jl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by nairu on 2016/11/1.
 */
@Entity
@Table(name = "tb_express_his")
public class ExpressHisEntity {
    @Id
    private long id;
    private Timestamp createTime;
    private String expressCode;

    public ExpressHisEntity(String expressCode) {
        this.expressCode = expressCode;
    }

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

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }
}
