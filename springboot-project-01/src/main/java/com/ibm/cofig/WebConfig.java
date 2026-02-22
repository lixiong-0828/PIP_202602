package com.ibm.cofig;

import com.ibm.interceptor.DemoInterceptor;
import com.ibm.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类：配置拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private  DemoInterceptor demoInterceptor;

    @Autowired
    private TokenInterceptor tokenInterceptor;


    /**
     * 【addPathPatterns ：追加拦截对象】
     * ；"/**" 拦截所有。1级，2级 目录都拦截
     * ："/*" 拦截一级路径 例："/depts/1" <-- 像这种2级路径拦截不了。只能拦截 --> "/emp","/depts" 等
     * : "/depts/*"  <--  这个只能拦截【depts】目录下面的请求
     *
     * 【excludePathPatterns ：拦截对象外】
     *
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");  //【/** 拦截所有】
//    }
}
