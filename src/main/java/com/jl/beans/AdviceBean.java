package com.jl.beans;

import com.jl.entity.AdviceEntity;
import com.jl.utils.Constants;

import java.sql.Date;

/**
 * Created by fannairu on 2016/8/2.
 */
public class AdviceBean {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AdviceEntity toAdviceEntity(Long userId) {
        AdviceEntity adviceEntity = new AdviceEntity();
        adviceEntity.setContent(this.getContent());
        adviceEntity.setCreateTime(new Date(System.currentTimeMillis()));
        adviceEntity.setStatus(Constants.INIT_STATUS);
        adviceEntity.setUserId(userId);

        return adviceEntity;
    }
}
