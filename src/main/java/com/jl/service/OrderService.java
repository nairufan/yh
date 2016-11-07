package com.jl.service;

import com.jl.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by fannairu on 2016/6/25.
 */
public interface OrderService extends PagingAndSortingRepository<OrderEntity, Long> {
    public Iterable<OrderEntity> findByUserId(long userId);

    public Page<OrderEntity> findByUserId(long userId, Pageable pageable);

    @Query(value = "select date_format(DATE(createTime), '%Y-%m-%d') as date, count(*) as counter\n" +
            "from OrderEntity \n" +
            "where createTime between :start and :end\n" +
            "group by DATE(createTime)")
    public Stream<Map> getStatisticsByDateRange(@Param("start") Timestamp start, @Param("end") Timestamp end);
}
