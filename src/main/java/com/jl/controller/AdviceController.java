package com.jl.controller;

import com.jl.beans.AdviceBean;
import com.jl.entity.UserEntity;
import com.jl.model.AdviceModel;
import com.jl.model.assembles.AdviceAssemble;
import com.jl.model.assembles.UserAssemble;
import com.jl.service.AdviceService;
import com.jl.entity.AdviceEntity;
import com.jl.service.UserService;
import com.jl.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by fannairu on 2016/8/2.
 */
@Component
@Path("advice")
@Produces("application/json")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;
    @Autowired
    private AdviceAssemble adviceAssemble;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAssemble userAssemble;
    @Autowired
    private HttpSession session;

    @POST
    @Consumes("application/json")
    public Map create(AdviceBean adviceBean) {
        Map reMap = new HashMap();
        AdviceEntity adviceEntity = new AdviceEntity();
        adviceEntity.setContent(adviceBean.getContent());
        adviceEntity.setCreateTime(new Date(System.currentTimeMillis()));
        adviceEntity.setStatus(Constants.INIT_STATUS);
        adviceEntity.setUserId((Long) session.getAttribute(Constants.USER_ID));
        adviceService.save(adviceEntity);
        reMap.put(Constants.RESULT, Constants.SUCCESS);
        return reMap;
    }

    @POST
    @Path("resolve/{id}")
    @Consumes("application/json")
    public Map resolve(@PathParam("id") long id) {
        Map reMap = new HashMap();
        AdviceEntity adviceEntity = adviceService.findOne(id);
        adviceEntity.setStatus(Constants.RESOLVE);
        adviceService.save(adviceEntity);
        reMap.put(Constants.RESULT, Constants.SUCCESS);
        return reMap;
    }

    @GET
    @Path("list")
    public Map getUserList(@QueryParam("start") int start, @QueryParam("size") int size,
                           @QueryParam("sort") String sort, @QueryParam("status") int status) {
        Map reMap = new HashMap();
        if (sort == null || "".equals(sort)) {
            sort = "ASC";
        }
        Sort sortAble = new Sort(Collections.singletonList(new Sort.Order(Sort.Direction.fromString(sort), "createTime")));
        Pageable pageable = new PageRequest(start, size, sortAble);
        Page<AdviceEntity> adviceEntities;
        if (status !=-1) {
            adviceEntities = adviceService.findByStatus(status, pageable);
        } else {
            adviceEntities = adviceService.findAll(pageable);
        }
        List<AdviceModel> adviceModels = adviceAssemble.assembleAdviceModelList(adviceEntities);
        List<Long> userIds = new ArrayList<Long>();
        for (AdviceModel adviceModel : adviceModels) {
            userIds.add(adviceModel.getUserId());
        }
        Iterable<UserEntity> userEntities = userService.findAll(userIds);
        reMap.put("list", adviceModels);
        reMap.put("userMap", userAssemble.assembleUserModelMap(userEntities));
        reMap.put("totalPages", adviceEntities.getTotalPages());
        return reMap;
    }
}
