package com.jl;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            "/login.html",
            "/build/**",
            "/orderdetail.html",
            "/express.html",
            "/orderdetail.js",
            "/MP_verify_h0emLIGJ0SsISIPS.txt",
    };

    private String[] adminUrls = {
            "/",
            "/index.html",
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

        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                String accept = request.getHeader("accept");
                if (accept != null && accept.indexOf("text/html") >= 0) {
                    response.sendRedirect("/login.html");
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
                }
            }
        });
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
