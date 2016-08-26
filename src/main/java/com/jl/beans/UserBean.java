package com.jl.beans;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.jl.entity.UserEntity;
import com.jl.utils.Constants;
import com.jl.utils.Md5;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Created by fannairu on 2016/6/22.
 */
@XmlRootElement
public class UserBean {
    private String tel;
    private String password;
    private String checkcode;
    private String avatar;
    private String username;
    private Integer gender;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public UserEntity toUserEntity() {
        UserEntity userEntity = new UserEntity();
        String username = this.getUsername();
        if (username == null || "".endsWith(username)) {
            username = this.getTel();
        }
        userEntity.setTel(this.getTel());
        userEntity.setCreateTime(new Date(System.currentTimeMillis()));
        userEntity.setGender(this.getGender());
        userEntity.setAvatar(this.getAvatar());
        userEntity.setUsername(username);
        userEntity.setRole(Constants.ROLE_USER);
        userEntity.setPassword(new Md5().encrypt(this.getPassword()));
        return userEntity;
    }
}
