package com.jl;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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
            "/api/user/wx-login",
            "/api/user/wx-bind",
            "/api/user/loginWithPassword",
            "/api/order/detail",
            "/api/express/**",
            "/api/message/**",
            "/index.html",
            "/login.html",
            "/build/**",
            "/orderdetail.html",
            "/express.html",
            "/orderdetail.js",
            "/",
            "/MP_verify_h0emLIGJ0SsISIPS.txt",
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
                .anyRequest().hasAnyRole(new String[]{"USER", "ADMIN"})
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/api/error/multiple-login")
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry())
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    // Work around https://jira.spring.io/browse/SEC-2855
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
