package com.jl.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by fannairu on 2016/8/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest extends BaseTest {
    @Test
    public void create() throws IOException {
        Map<String, String> orderItem = new HashMap();
        orderItem.put("id", "8111");
        orderItem.put("order_id", "1111111111112");
        orderItem.put("goods_id", "123");
        orderItem.put("amount", "12");
        orderItem.put("create_time", "1467033265632");
        orderItem.put("update_time", "1467033265632");
        List<Map> orderItems = new LinkedList<Map>();
        orderItems.add(orderItem);

        Map<String, Object> content = new HashMap();
        content.put("id", "1111111111112");
        content.put("customer_id", "11111111111");
        content.put("status", "1");
        content.put("memo", "bei zhu");
        content.put("express_number", "210001633605");
        content.put("express_name", "ANE");
        content.put("exist_status","1");
        content.put("create_time", "1467033265632");
        content.put("update_time", "1467033265632");
        content.put("customer_name", "tom");
        content.put("customer_address", "address1");
        content.put("customer_tel", "1222222");
        content.put("items", orderItems);


        HttpEntity request = new HttpEntity(content, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("/api/order", request, Map.class);
        Map result = (Map) responseEntity.getBody().get("Result");
        assertThat(result.get("id").toString().endsWith(content.get("id").toString()));
    }
}
