package com.jl;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by fannairu on 2016/7/21.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private String[] permitAllUrls = {
            "/api/user/register",
            "/api/user/login",
            "/api/order/detail",
            "/api/express",
            "/api/message/**",
            "/index.html",
            "/login.html",
            "/build/**",
            "/orderdetail.html",
            "/orderdetail.js",
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(permitAllUrls).permitAll()
                .anyRequest().hasRole("USER");
    }
}
