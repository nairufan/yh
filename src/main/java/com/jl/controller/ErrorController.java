package com.jl.controller;

import com.jl.utils.Constants;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by fannairu on 2016/9/28.
 */
@Component
@Path("error")
@Produces("application/json")
public class ErrorController {
    @GET
    @Path("multiple-login")
    public Map getOrderList() {
        Map reMap = new HashMap();
        reMap.put(Constants.ERROR_CODE, Constants.ERROR_MULTIPLE_LOGIN);
        return reMap;
    }
}
