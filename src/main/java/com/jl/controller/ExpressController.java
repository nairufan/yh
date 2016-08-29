package com.jl.controller;

import com.jl.utils.KdniaoTrackQueryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by fannairu on 2016/7/24.
 */
@Component
@Path("express")
public class ExpressController {
    @Autowired
    private KdniaoTrackQueryAPI kdniaoTrackQueryAPI;

    @GET
    @Cacheable("express")
    public String query(@QueryParam("expressCode") String expressCode, @QueryParam("expressNumber") String expressNumber) {
        try {
            return kdniaoTrackQueryAPI.getOrderTracesByJson(expressCode, expressNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
