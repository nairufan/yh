package com.jl.service;

import com.jl.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by fannairu on 2016/6/25.
 */
public interface CategoryService extends PagingAndSortingRepository<CategoryEntity, Long> {
    public Iterable<CategoryEntity> findByUserId(long userId);
    public Page<CategoryEntity> findByUserId(long userId, Pageable pageable);
}
