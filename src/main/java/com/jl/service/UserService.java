package com.jl.service;
import com.jl.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by fannairu on 2016/6/21.
 */
public interface UserService extends PagingAndSortingRepository<UserEntity, Long> {
    public UserEntity findByTel(String phone);
    public UserEntity findByOpenId(String openid);
}
