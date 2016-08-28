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
public class AdviceControllerTest extends BaseTest {
    @Test
    public void create() throws IOException {
        Map<String, String> content = new HashMap();
        content.put("content", "this is an advice");
        HttpEntity request = new HttpEntity(content, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("/api/advice", request, Map.class);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }
}
