package com.orange.base.controller;


import com.orange.base.entity.JSONResult;
import com.orange.base.service.CheckLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/global/user")
public class UserController {

    @Autowired
    CheckLoginService checkLoginService ;

    @RequestMapping("/doLogin")
    @ResponseBody
    public JSONResult doLogin(HttpServletRequest req, HttpServletResponse res){
        String userId = req.getParameter("userId") ;
        String passWord = req.getParameter("passWord") ;
        HttpSession session = req.getSession() ;
        return checkLoginService.check(userId,passWord,session) ;
    }
}
