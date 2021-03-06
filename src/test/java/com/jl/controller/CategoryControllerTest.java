package com.jl.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nairu on 2016/8/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends BaseTest{

    @Test
    public void create() throws IOException {
        Map<String, String> content = new HashMap();
        content.put("id", "11111111");
        content.put("name", "衣服12");
        content.put("memo", "这是描述");
        content.put("create_time", "1467033265632");
        content.put("update_time", "1467033265632");

        HttpEntity request = new HttpEntity(content, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("/api/category", request, Map.class);
        Map result = (Map) responseEntity.getBody().get("Result");
        assertThat(result.get("name").toString().endsWith(content.get("name")));
    }
}
