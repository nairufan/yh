package com.jl.controller;

import com.jl.beans.SettingsBean;
import com.jl.entity.SettingsEntity;
import com.jl.model.assembles.SettingsAssemble;
import com.jl.service.SettingsService;
import com.jl.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fannairu on 2016/7/17.
 */
@Component
@Path("settings")
@Produces("application/json")
public class SettingsController {
    @Autowired
    private SettingsService settingsService;
    @Autowired
    private SettingsAssemble settingsAssemble;
    @Autowired
    private HttpSession session;

    @POST
    public Map create(SettingsBean settingsBean) {
        Map reMap = new HashMap<String, Object>();
        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        SettingsEntity entity = settingsService.findByKeyAndUserId(settingsBean.getStringKey(), userId);
        if (entity == null) {
            entity = new SettingsEntity();
        }
        entity.setStringKey(settingsBean.getStringKey());
        entity.setStringValue(settingsBean.getStringValue());
        entity.setUserId(userId);
        entity = settingsService.save(entity);
        reMap.put(Constants.RESULT, settingsAssemble.assembleSettingsModel(entity));
        return reMap;
    }

}
