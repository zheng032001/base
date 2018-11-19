package com.orange.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统级页面跳转
 */
@Controller
@RequestMapping("/global")
public class SystemController {
    /**
     *  登录页面
     */
    @RequestMapping("/login")
    public String login(){
        return "global/login";
    }
}
