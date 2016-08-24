package com.jl.service;

import com.jl.entity.SettingsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by fannairu on 2016/7/17.
 */
public interface SettingsService extends CrudRepository<SettingsEntity, Long> {
    @Query(value = "select s from SettingsEntity s where s.stringKey=:stringKey and s.userId=:userId")
    public SettingsEntity findByKeyAndUserId(@Param("stringKey") String stringKey, @Param("userId") long userId);
    public Iterable<SettingsEntity> findByUserId(long userId);
}
