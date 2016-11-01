package com.jl.controller;

import com.jl.entity.ExpressHisEntity;
import com.jl.service.ExpressHisService;
import com.jl.utils.ExpressCode;
import com.jl.utils.KdniaoTrackQueryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.Map;
import java.util.HashMap;

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
}
