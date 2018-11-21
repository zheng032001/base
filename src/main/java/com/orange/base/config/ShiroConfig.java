package com.orange.base.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager ;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置默认登录页面
        shiroFilterFactoryBean.setLoginUrl("/global/login");
        // 设置登录成功跳转页面
        shiroFilterFactoryBean.setSuccessUrl("");
        // 设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("");
        // 拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/global/user/doLogin", "anon");

        // 配置退出过滤器,其中的具体的退出代码shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


}
