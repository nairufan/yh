package com.jl.service;

import com.jl.entity.GoodsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by fannairu on 2016/6/25.
 */
public interface GoodsService extends PagingAndSortingRepository<GoodsEntity, Long> {
    public Iterable<GoodsEntity> findByUserId(long userId);

    public Page<GoodsEntity> findByUserId(long userId, Pageable pageable);

    public Page<GoodsEntity> findByUserIdAndCategoryId(long userId, long categoryId, Pageable pageable);
}
