package com.jl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.beans.UserBean;
import com.jl.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by nairu on 2016/8/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();

    @Before
    public void setup() {
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void loginAndCreate() throws IOException {
        UserBean userBean = new UserBean();
        userBean.setTel("13524715428");
        userBean.setCheckcode("0000");
        userBean.setGender(1);
        userBean.setUsername("admin");
        HttpEntity request = new HttpEntity(userBean, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("/api/user/login", request, Map.class);
        Map result = (Map) responseEntity.getBody().get("Result");
        assertThat(result.get("username").toString().endsWith(userBean.getUsername()));
    }

}
