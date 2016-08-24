package com.jl.service;

import com.jl.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by fannairu on 2016/6/25.
 */
public interface OrderService extends PagingAndSortingRepository<OrderEntity, Long> {
    public Iterable<OrderEntity> findByUserId(long userId);

    public Page<OrderEntity> findByUserId(long userId, Pageable pageable);
}
