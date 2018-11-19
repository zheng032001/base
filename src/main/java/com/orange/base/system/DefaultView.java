package com.orange.base.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  设置默认页面
 */
/*
  使用笔记：
    1、@EnableWebMvc+extends WebMvcConfigurationAdapter
        在扩展的类中重写父类的方法即可，这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
    2、extends WebMvcConfigurationSupport
        在扩展的类中重写父类的方法即可，这种方式会屏蔽springboot的@EnableAutoConfiguration中的设置
    3、extends WebMvcConfigurationAdapter
        在扩展的类中重写父类的方法即可，这种方式依旧使用springboot的@EnableAutoConfiguration中的设置
 */
@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("forward:/global/login") ;
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
}
