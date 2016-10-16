package com.jl.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.beans.*;
import com.jl.entity.OrderEntity;
import com.jl.entity.OrderItemEntity;
import com.jl.entity.UserEntity;
import com.jl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fannairu on 2016/7/31.
 */
@Component
public class DataLoader {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    public UserEntity createUser(String user) throws IOException {
        UserBean userBean = objectMapper.readValue(user, UserBean.class);
        return userService.save(userBean.toUserEntity());
    }

    public void createGoods(Long userId) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/goods").toURI()), StandardCharsets.UTF_8);
        for (String c : lines) {
            GoodsBean goodsBean = objectMapper.readValue(c, GoodsBean.class);
            goodsService.save(goodsBean.toGoodsEntity(userId));
        }
    }

    public void createCategory(Long userId) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/category").toURI()), StandardCharsets.UTF_8);
        for (String c : lines) {
            CategoryBean categoryBean = objectMapper.readValue(c, CategoryBean.class);
            categoryService.save(categoryBean.toCategoryEntity(userId));
        }
    }

    public void createCustomer(Long userId) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/customer").toURI()), StandardCharsets.UTF_8);
        for (String c : lines) {
            CustomerBean customerBean = objectMapper.readValue(c, CustomerBean.class);
            customerService.save(customerBean.toCustomerEntity(userId));
        }
    }

    public void createOrder(Long userId) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("C:/workspace_java/data/order").toURI()), StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for (String c : lines) {
            sb.append(c);
        }
        List<OrderBean> list = objectMapper.readValue(sb.toString().getBytes(), List.class);
        List<OrderItemEntity> orderItemEntities = new ArrayList<OrderItemEntity>();
        List<OrderEntity> orderEntities = new ArrayList();
        for (OrderBean orderBean : list) {
            for (OrderItemBean orderItemBean : orderBean.getItems()) {
                OrderItemEntity orderItemEntity = orderItemBean.toOrderItemEntity(userId);
                orderItemEntities.add(orderItemEntity);
            }
            OrderEntity orderEntity = orderBean.toOrderEntity(userId);
            orderEntities.add(orderEntity);
        }
        orderItemService.save(orderItemEntities);
        orderService.save(orderEntities);
    }

    public void load() throws IOException, URISyntaxException {
        DataLoader dataLoader = new DataLoader();
        List<String> lines = Files.readAllLines(Paths.get(DataLoader.class.getResource("/resources/data/users").toURI()), StandardCharsets.UTF_8);
        for (String user : lines) {
            UserEntity userEntity = dataLoader.createUser(user);
            dataLoader.createCategory(userEntity.getId());
            dataLoader.createGoods(userEntity.getId());
            dataLoader.createCustomer(userEntity.getId());
            dataLoader.createOrder(userEntity.getId());
        }
    }
}

