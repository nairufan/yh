package com.jl.beans;

import com.jl.entity.UserEntity;
import com.jl.utils.Constants;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by nairu on 2016/10/24.
 */
public class WxLoginBean {
    private String openId;
    private String tel;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Math.abs(UUID.randomUUID().getMostSignificantBits()));
        userEntity.setTel(this.getTel());
        userEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setUsername(this.getTel());
        userEntity.setRole(Constants.ROLE_USER);
        return userEntity;
    }
}
