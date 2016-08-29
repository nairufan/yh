package com.jl;

import com.jl.dataloader.DataLoader;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by fannairu on 2016/6/17.
 */
@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableCaching
public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException {
        SpringApplication.run(Application.class, args);
    }


}
