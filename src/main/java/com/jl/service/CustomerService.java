package com.jl.service;

import com.jl.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by fannairu on 2016/6/25.
 */
public interface CustomerService extends PagingAndSortingRepository<CustomerEntity, Long> {
    public Iterable<CustomerEntity> findByUserId(long userId);
    public Page<CustomerEntity> findByUserId(long userId, Pageable pageable);
}
