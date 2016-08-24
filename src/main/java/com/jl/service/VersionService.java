package com.jl.service;

import com.jl.entity.VersionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fannairu on 2016/7/10.
 */
public interface VersionService extends CrudRepository<VersionEntity, Long> {
    public VersionEntity findByUserId(long userId);
}
