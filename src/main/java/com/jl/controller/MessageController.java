package com.jl.controller;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.jl.properties.YunLianTongProperties;
import com.jl.utils.CheckCodeGenerator;
import com.jl.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fannairu on 2016/7/10.
 */
@Component
@Path("message")
@Produces("application/json")
public class MessageController {
    @Autowired
    private CheckCodeGenerator generator;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    @Autowired
    private HttpSession session;
    @Autowired
    private YunLianTongProperties yunLianTongProperties;

    @POST
    @Path("send/{tel}")
    public Map sendMessage(@PathParam("tel") final String tel) {
        Map reMap = new HashMap<String, Object>();
        final String codes = generator.getCodes(4);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Map map = sendSms(tel, codes, String.valueOf(Constants.TIME_OUT));
                System.out.println(map);
            }
        });
        reMap.put(Constants.RESULT, Constants.SUCCESS);
        session.setAttribute(Constants.CHECK_CODE, codes);
        session.setAttribute(Constants.SEND_TIME, System.currentTimeMillis());
        return reMap;
    }

    private Map sendSms(String tel, String checkcode, String timeout) {
        CCPRestSDK restAPI = new CCPRestSDK();
        restAPI.init("app.cloopen.com", "8883");
        restAPI.setAccount(yunLianTongProperties.getAccount(), yunLianTongProperties.getToken());
        restAPI.setAppId(yunLianTongProperties.getAppid());
        return restAPI.sendTemplateSMS(tel, yunLianTongProperties.getTemplate(), new String[]{checkcode, timeout});
    }
}
