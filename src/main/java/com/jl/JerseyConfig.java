package com.jl;

import com.jl.controller.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * Created by fannairu on 2016/6/21.
 */
@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        register(UserController.class);
        register(CategoryController.class);
        register(CustomerController.class);
        register(GoodsController.class);
        register(OrderController.class);
        register(DataSync.class);
        register(MessageController.class);
        register(SettingsController.class);
        register(ExpressController.class);
        register(AdviceController.class);
        register(ImageController.class);
    }
}
