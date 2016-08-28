package com.jl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;

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
            "/api/user/loginWithPassword",
            "/api/order/detail",
            "/api/express",
            "/api/message/**",
            "/index.html",
            "/login.html",
            "/build/**",
            "/orderdetail.html",
            "/orderdetail.js",
    };

    private String[] adminUrls = {
            "/index.html",
            "login.html",
            "detail.html",
            "advice.html"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(permitAllUrls).permitAll()
                .antMatchers(adminUrls).hasRole("ADMIN")
                .anyRequest().hasAnyRole(new String[]{"USER", "ADMIN"});
    }
}
