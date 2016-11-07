package com.jl.service;

import com.jl.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Map;
import java.util.stream.Stream;


/**
 * Created by fannairu on 2016/6/21.
 */
public interface UserService extends PagingAndSortingRepository<UserEntity, Long> {
    public UserEntity findByTel(String phone);

    public UserEntity findByOpenId(String openid);

    @Query(value = "select date_format(DATE(createTime), '%Y-%m-%d') as date, count(*) as counter\n" +
            "from UserEntity \n" +
            "where createTime between :start and :end\n" +
            "group by DATE(createTime)")
    public Stream<Map> getStatisticsByDateRange(@Param("start") Timestamp start, @Param("end") Timestamp end);
}
