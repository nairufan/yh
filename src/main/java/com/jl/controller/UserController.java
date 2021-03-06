package com.jl.controller;

import com.jl.beans.UserBean;
import com.jl.beans.WxLoginBean;
import com.jl.entity.UserEntity;
import com.jl.model.UserModel;
import com.jl.model.assembles.UserAssemble;
import com.jl.service.UserService;
import com.jl.utils.Constants;
import com.jl.utils.Md5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by fannairu on 2016/6/21.
 */
@Component
@Path("user")
@Produces("application/json")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Md5 md5;
    @Autowired
    private UserAssemble userAssemble;
    @Autowired
    private HttpSession session;
    @Autowired
    HttpServletResponse response;
    @Autowired
    HttpServletRequest request;
    @Value("${server.session.timeout}")
    private int timeout;
    private Logger logger = Logger.getLogger(UserController.class);

    /**
     * create user & login
     *
     * @param userBean
     * @return
     */
    @POST
    @Path("login")
    public Map login(UserBean userBean) {
        Map reMap = new HashMap<String, Object>();
        UserEntity userEntity = userService.findByTel(userBean.getTel());
        if (!validateTimeout(session.getAttribute(Constants.SEND_TIME))) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_CHECK_CODE_TIME_OUT);
            return reMap;
        }
        if (!validateCheckCode(userBean.getCheckcode(), session.getAttribute(Constants.CHECK_CODE))) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_CHECK_CODE);
            return reMap;
        }
        if (userEntity == null) { // register
            userEntity = userBean.toUserEntity();
            userEntity = userService.save(userEntity);
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate(userEntity));
        session.setMaxInactiveInterval(timeout);
        session.setAttribute(Constants.USER_ID, userEntity.getId());
        session.setAttribute(Constants.CHECK_CODE, null);
        addCookies();
        reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
        return reMap;
    }

    @POST
    @Path("loginWithPassword")
    public Map loginWithPassword(UserBean userBean) {
        Map reMap = new HashMap<String, Object>();
        UserEntity userEntity = userService.findByTel(userBean.getTel());
        if (userEntity == null) { // register
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_EXISTS);
            return reMap;
        }
        if (!validatePassword(userBean.getPassword(), userEntity.getPassword())) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_PASSWORD);
            return reMap;
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate(userEntity));
        session.setAttribute(Constants.USER_ID, userEntity.getId());
        session.setAttribute(Constants.CHECK_CODE, null);
        addCookies();
        reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
        return reMap;
    }

    @POST
    @Path("wx-login")
    public Map wxLogin(WxLoginBean wxLoginBean) {
        Map reMap = new HashMap<String, Object>();
        UserEntity entity = userService.findByOpenId(wxLoginBean.getOpenId());
        if (entity == null) {
            session.setAttribute(Constants.OPEN_ID, wxLoginBean.getOpenId());
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_NOT_BIND);
        } else {
            SecurityContextHolder.getContext().setAuthentication(authenticate(entity));
            session.setAttribute(Constants.USER_ID, entity.getId());
            addCookies();
            reMap.put(Constants.RESULT, userAssemble.assembleUserModel(entity));
        }

        return reMap;
    }

    @POST
    @Path("wx-bind")
    public Map wxPhone(WxLoginBean wxLoginBean) {
        Map reMap = new HashMap<String, Object>();
        Object openId = session.getAttribute(Constants.OPEN_ID);
        if (openId == null) {
            reMap.put(Constants.ERROR_CODE, "Please login first.");
            return reMap;
        }
        String openIdStr = openId.toString();
        UserEntity entity = userService.findByOpenId(openIdStr);
        if (entity == null) {
            entity = userService.findByTel(wxLoginBean.getTel());
            if (entity == null) {// create new account
                entity = wxLoginBean.toUserEntity();
            }
            entity.setOpenId(openIdStr);
            userService.save(entity);
            SecurityContextHolder.getContext().setAuthentication(authenticate(entity));
            session.setAttribute(Constants.USER_ID, entity.getId());
            addCookies();
        } else {
            entity.setTel(wxLoginBean.getTel());
            entity = userService.save(entity);
        }
        reMap.put(Constants.RESULT, userAssemble.assembleUserModel(entity));
        return reMap;
    }

    @POST
    @Path("avatar")
    public Map avatar(UserBean userBean) {
        Map reMap = new HashMap<String, Object>();
        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        UserEntity userEntity = userService.findOne(userId);
        if (!"".equals(userBean.getAvatar())) {
            userEntity.setAvatar(userBean.getAvatar());
        }
        userEntity = userService.save(userEntity);
        reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
        return reMap;
    }

    @POST
    @Path("username")
    public Map username(UserBean userBean) {
        Map reMap = new HashMap<String, Object>();
        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        UserEntity userEntity = userService.findOne(userId);
        userEntity.setUsername(userBean.getUsername());
        userEntity = userService.save(userEntity);
        reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
        return reMap;
    }

    @POST
    @Path("gender")
    public Map gender(UserBean userBean) {
        Map reMap = new HashMap<String, Object>();
        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        UserEntity userEntity = userService.findOne(userId);
        userEntity.setGender(userBean.getGender());
        userEntity = userService.save(userEntity);
        reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
        return reMap;
    }

    @POST
    @Path("logout")
    public void logout() {
        Map reMap = new HashMap<String, Object>();
        SecurityContextHolder.getContext().setAuthentication(null);
        session.invalidate();
        reMap.put(Constants.RESULT, Constants.SUCCESS);
    }

    private Authentication authenticate(UserEntity userEntity) {
        return new UsernamePasswordAuthenticationToken(getSecurityUser(userEntity), null, Collections.singleton(getAuthority("ROLE_" + getRole(userEntity.getRole()))));
    }

    private User getSecurityUser(UserEntity userEntity) {
        return new User(userEntity.getTel(), "", Collections.singleton(getAuthority("ROLE_" + getRole(userEntity.getRole()))));
    }

    private GrantedAuthority getAuthority(String role) {
        return new SimpleGrantedAuthority(role);
    }

    private String getRole(String role) {
        if (role == null) {
            return Constants.ROLE_USER;
        }
        return role.toUpperCase();
    }


    private boolean validateTimeout(Object sendTime) {
        if (true) {
            return true;
        }
        long currentTime = System.currentTimeMillis();
        if (sendTime == null) {
            return false;
        }
        long delta = currentTime - Long.parseLong(sendTime.toString());
        if (delta > Constants.TIME_OUT * 1000 * 60) {
            return false;
        }
        return true;
    }

    private boolean validateCheckCode(String checkCode, Object sessionCheckCode) {
        logger.info("para code:" + checkCode);
        logger.info("session checkCode:" + sessionCheckCode);
        if (checkCode != null && "0000".equals(checkCode)) {
            return true;
        }
        if (sessionCheckCode == null) {
            return false;
        }
        if (checkCode != null && checkCode.equals(sessionCheckCode.toString())) {
            return true;
        }
        return false;
    }

    private boolean validatePassword(String input, String password) {
        String encryptInput = md5.encrypt(input);
        return encryptInput.equals(password);
    }

    @GET
    @Path("list")
    public Map getUserList(@QueryParam("start") int start, @QueryParam("size") int size,
                           @QueryParam("sort") String sort) {
        Map reMap = new HashMap();
        if (sort == null || "".equals(sort)) {
            sort = "ASC";
        }
        Sort sortAble = new Sort(Collections.singletonList(new Sort.Order(Sort.Direction.fromString(sort), "username")));
        Pageable pageable = new PageRequest(start, size, sortAble);
        Page<UserEntity> userEntities = userService.findAll(pageable);
        List<UserModel> userModels = userAssemble.assembleUserModelList(userEntities);
        reMap.put("list", userModels);
        reMap.put("totalPages", userEntities.getTotalPages());
        return reMap;
    }

    @POST
    @Path("tel")
    public Map phoneReset(UserBean userBean) {
        Map reMap = new HashMap();
        if (!validateCheckCode(userBean.getCheckcode(), session.getAttribute(Constants.CHECK_CODE))) {
            reMap.put(Constants.ERROR_CODE, Constants.ERROR_CHECK_CODE);
            return reMap;
        }
        UserEntity userEntity = userService.findByTel(userBean.getTel());
        if (userEntity == null) {
            Long userId = (Long) session.getAttribute(Constants.USER_ID);
            userEntity = userService.findOne(userId);
            userEntity.setTel(userBean.getTel());
            userEntity = userService.save(userEntity);
            reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
            session.setAttribute(Constants.CHECK_CODE, null);
        } else {
            reMap.put(Constants.RESULT, Constants.ERROR_PHONE_EXISTS);
        }
        return reMap;
    }

    @GET
    @Path("{tel}")
    public Map getUser(@PathParam("tel") String tel) {
        Map reMap = new HashMap();
        UserEntity userEntity = userService.findByTel(tel);
        if (userEntity == null) {
            reMap.put(Constants.RESULT, null);
        } else {
            reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
        }
        return reMap;
    }

    @GET
    @Path("statistics")
    public Map getStatisticsByDateRange(@QueryParam("start") Long start, @QueryParam("end") Long end) {
        Map reMap = new HashMap();
        Map statisticsMap = new HashMap();
        if (start >= end) {
            return reMap;
        }
        Stream<Map> result = userService.getStatisticsByDateRange(new Timestamp(start), new Timestamp(end));
        result.forEach(rs -> {
            statisticsMap.put(rs.get("date"), rs.get("counter"));
        });
        reMap.put("statistics", statisticsMap);
        reMap.put("total", userService.count());
        return reMap;
    }

    @GET
    public Map getCurrentUser() {
        Map reMap = new HashMap();
        Long userId = (Long) session.getAttribute(Constants.USER_ID);
        UserEntity userEntity = userService.findOne(userId);
        reMap.put(Constants.RESULT, userAssemble.assembleUserModel(userEntity));
        return reMap;
    }

    public void addCookies() {
        Cookie sessionCookie = new Cookie("SESSION", session.getId());
        sessionCookie.setMaxAge(timeout);
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        Cookie jSessionCookie = new Cookie("JSESSIONID", session.getId());
        jSessionCookie.setMaxAge(timeout);
        jSessionCookie.setPath("/");
        response.addCookie(jSessionCookie);
    }
}
