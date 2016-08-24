package com.jl.service;

import com.jl.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by fannairu on 2016/6/25.
 */
public interface OrderItemService extends PagingAndSortingRepository<OrderItemEntity, Long> {
    public Iterable<OrderItemEntity> findByUserId(long userId);

    public Iterable<OrderItemEntity> findByOrderId(long orderId);

    @Query(value = "select o from OrderItemEntity o where o.orderId in (:orderIds)")
    public Iterable<OrderItemEntity> findByOrderIds(@Param("orderIds") Iterable<Long> orderIds);
}
