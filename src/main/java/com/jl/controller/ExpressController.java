package com.jl.controller;

import com.jl.entity.ExpressHisEntity;
import com.jl.service.ExpressHisService;
import com.jl.utils.ExpressCode;
import com.jl.utils.KdniaoTrackQueryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by fannairu on 2016/7/24.
 */
@Component
@Path("express")
public class ExpressController {
    @Autowired
    private KdniaoTrackQueryAPI kdniaoTrackQueryAPI;
    private Map codeMap = ExpressCode.getCodeMap();
    @Autowired
    private ExpressHisService expressHisService;

    @GET
    @Produces("application/json")
    @Cacheable("express")
    public Map queryExpress(@QueryParam("expressNumber") String expressNumber) {
        Map<String, String> reMap = new HashMap();
        String info = "";
        try {
            info = kdniaoTrackQueryAPI.getOrderByExpressNumber(expressNumber);
            expressHisService.save(new ExpressHisEntity(expressNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }
        reMap.put("express", info);
        return reMap;
    }


    @GET
    @Path("shipper/{expressNumber}")
    @Produces("application/json")
    public Map query(@PathParam("expressNumber") String expressNumber) throws Exception {
        Map reMap = new HashMap();
        String code = kdniaoTrackQueryAPI.getShipperByExpressNumber(expressNumber);
        Object codeObj = codeMap.get(code);
        String realCode = code;
        if (codeObj != null) {
            realCode = codeObj.toString();
        }
        expressHisService.save(new ExpressHisEntity(expressNumber));
        reMap.put("code", realCode);
        return reMap;
    }

    @GET
    @Produces("application/json")
    @Path("statistics")
    public Map getStatisticsByDateRange(@QueryParam("start") Long start, @QueryParam("end") Long end) {
        Map reMap = new HashMap();
        Map statisticsMap = new HashMap();
        if (start >= end) {
            return reMap;
        }
        Stream<Map> result = expressHisService.getStatisticsByDateRange(new Timestamp(start), new Timestamp(end));
        result.forEach(rs -> {
            statisticsMap.put(rs.get("date"), rs.get("counter"));
        });
        reMap.put("statistics", statisticsMap);
        reMap.put("total", expressHisService.count());
        return reMap;
    }
}
