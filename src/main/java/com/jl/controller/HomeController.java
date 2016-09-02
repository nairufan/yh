package com.jl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nairu on 2016/9/1.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "forward:/login.html";
    }
}
