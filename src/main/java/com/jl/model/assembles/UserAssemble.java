package com.jl.model.assembles;

import com.jl.entity.UserEntity;
import com.jl.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by fannairu on 2016/6/23.
 */
@Component
public class UserAssemble {
    public UserModel assembleUserModel(UserEntity entity) {
        UserModel model = new UserModel();
        model.setId(new Long(entity.getId()).toString());
        model.setCreateTime(entity.getCreateTime());
        model.setTel(entity.getTel());
        model.setAvatar(entity.getAvatar());
        model.setUsername(entity.getUsername());
        model.setRole(entity.getRole());
        model.setGender(entity.getGender());
        model.setOpenId(entity.getOpenId());
        return model;
    }

    public List<UserModel> assembleUserModelList(Iterable<UserEntity> userEntities) {
        List<UserModel> userModels = new ArrayList<UserModel>();
        for (UserEntity entity : userEntities) {
            userModels.add(assembleUserModel(entity));
        }
        return userModels;
    }

    public Map<Long, UserModel> assembleUserModelMap(Iterable<UserEntity> userEntities) {
        Map<Long, UserModel> userModelMap = new HashMap();
        for (UserEntity entity : userEntities) {
            userModelMap.put(entity.getId(), assembleUserModel(entity));
        }
        return userModelMap;
    }
}
