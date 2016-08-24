package com.jl.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Created by fannairu on 2016/7/31.
 */
public class DataLoader {
    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost/api/";

    public ResponseEntity<Map> createUser(HttpHeaders headers, String user) {
        HttpEntity request = new HttpEntity(user, headers);
        return restTemplate.postForEntity(baseUrl + "/user/register", request, Map.class);
    }

    public void createGoods(HttpHeaders headers) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/goods").toURI()), StandardCharsets.UTF_8);
        for (String c : lines) {
            HttpEntity request = new HttpEntity(c, headers);
            restTemplate.postForObject(baseUrl + "/goods", request, String.class);
        }
    }

    public void createCategory(HttpHeaders headers) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/category").toURI()), StandardCharsets.UTF_8);
        for (String c : lines) {
            HttpEntity request = new HttpEntity(c, headers);
            restTemplate.postForObject(baseUrl + "/category", request, String.class);
        }
    }

    public void createCustomer(HttpHeaders headers) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/customer").toURI()), StandardCharsets.UTF_8);
        for (String c : lines) {
            HttpEntity request = new HttpEntity(c, headers);
            restTemplate.postForObject(baseUrl + "/customer", request, String.class);
        }
    }

    public void createOrder(HttpHeaders headers) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/order").toURI()), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for (String c : lines) {
            sb.append(c);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map> list = objectMapper.readValue(sb.toString().getBytes(), List.class);
        for (Map order : list) {
            HttpEntity request = new HttpEntity(order, headers);
            restTemplate.postForObject(baseUrl + "/order", request, String.class);
        }
    }

//    public static void main(String[] args) throws IOException, URISyntaxException {
//        DataLoader dataLoader = new DataLoader();
//        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/users").toURI()), StandardCharsets.UTF_8);
//        for (String user : lines) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//            ResponseEntity<Map> response = dataLoader.createUser(headers, user);
////            headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(0));
////            dataLoader.createCategory(headers);
////            dataLoader.createGoods(headers);
////            dataLoader.creates
//// Customer(headers);
////            dataLoader.createOrder(headers);
//        }
//    }
}

