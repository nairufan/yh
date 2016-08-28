package com.jl.controller;

import com.jl.beans.UserBean;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fannairu on 2016/8/27.
 */
public class BaseTest {
    @Autowired
    protected TestRestTemplate restTemplate;
    protected HttpHeaders headers = new HttpHeaders();

    @Before
    public void setup() {
        List<MediaType> accepts = new ArrayList();
        accepts.add(MediaType.APPLICATION_JSON_UTF8);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(accepts);
        UserBean userBean = new UserBean();
        userBean.setTel("13524715428");
        HttpEntity request = new HttpEntity(userBean, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity("/api/user/login", request, Map.class);
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(0));
    }

    protected void test() {

    }
}
