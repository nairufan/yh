package com.jl.service;

import com.jl.entity.AdviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by fannairu on 2016/8/2.
 */
public interface AdviceService extends PagingAndSortingRepository<AdviceEntity, Long> {
    public Page<AdviceEntity> findByStatus(int status, Pageable pageable);

}
