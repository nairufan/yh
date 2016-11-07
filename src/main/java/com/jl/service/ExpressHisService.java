package com.jl.service;

import com.jl.entity.ExpressHisEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by nairu on 2016/11/1.
 */
public interface ExpressHisService extends CrudRepository<ExpressHisEntity, Long> {
    @Query(value = "select date_format(DATE(createTime), '%Y-%m-%d') as date, count(*) as counter\n" +
            "from ExpressHisEntity \n" +
            "where createTime between :start and :end\n" +
            "group by DATE(createTime)")
    public Stream<Map> getStatisticsByDateRange(@Param("start") Timestamp start, @Param("end") Timestamp end);
}
