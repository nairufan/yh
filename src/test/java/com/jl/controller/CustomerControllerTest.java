package com.jl.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by fannairu on 2016/8/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest extends BaseTest{
    @Test
    public void create() throws IOException {
        Map<String, String> content = new HashMap();
        content.put("id", "11111111111");
        content.put("customer_name", "xiao ming");
        content.put("customer_address", "shanghai huangpu");
        content.put("customer_tel", "124464545454");
        content.put("memo", "memo");
        content.put("pics", "");
        content.put("level","0");
        content.put("create_time", "1467033265632");
        content.put("update_time", "1467033265632");

        HttpEntity request = new HttpEntity(content, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("/api/customer", request, Map.class);
        Map result = (Map) responseEntity.getBody().get("Result");
        assertThat(result.get("id").toString().endsWith(content.get("id")));
    }
}
