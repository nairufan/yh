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
public class SettingsControllerTest extends BaseTest{
    @Test
    public void create() throws IOException {
        Map<String, String> content = new HashMap();
        content.put("string_key", "light");
        content.put("string_value", "on");
        HttpEntity request = new HttpEntity(content, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("/api/settings", request, Map.class);
        Map result = (Map) responseEntity.getBody().get("Result");
        assertThat(result.get("string_key").toString().endsWith(content.get("string_key")));
    }
}
