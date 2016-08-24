package com.jl.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

/**
 * Created by fannairu on 2016/6/23.
 */
public class UserModel {
    private long id;
    private String tel;
    private String username;
    private String avatar;
    @JsonProperty(value = "create_time")
    private Date createTime;
    private String role;
    private Integer gender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
