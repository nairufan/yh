package com.jl.controller;

import com.jl.beans.CategoryBean;
import com.jl.beans.UserBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nairu on 2016/8/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers = new HttpHeaders();

    @Before
    public void setup() {
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        UserBean userBean = new UserBean();
        userBean.setTel("13524715428");
        HttpEntity request = new HttpEntity(userBean, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity("/api/user/login", request, Map.class);
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(0));
    }

    @Test
    public void create() throws IOException {
        CategoryBean categoryBean = new CategoryBean();
        categoryBean.setName("衣服");
        categoryBean.setMemo("这是描述");
        categoryBean.setId(11111);
        categoryBean.setCreateTime(1467033265632L);
        categoryBean.setUpdateTime(1467033265632L);
        HttpEntity request = new HttpEntity(categoryBean, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/api/category", request, String.class);
        System.out.println(responseEntity.getBody());
//        Map result = (Map) responseEntity.getBody().get("Result");
//        assertThat(result.get("name").toString().endsWith(categoryBean.getName()));
    }
}
