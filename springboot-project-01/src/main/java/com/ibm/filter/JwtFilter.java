package com.ibm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter (urlPatterns = "/*")  // 拦截所有请求  //Java Web的原生组件。。想用在启动类里加注解
public class JwtFilter implements Filter {
    /**
     *      * 初始化时只调用一次
     *      * 默认方法，可以不用实现
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);

        log.info("=============jwtFilter:init 初始化you了===============");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("=============jwtFilter:doFilter  拦截到你了===============");


        log.info("=============jwtFilter:doFilter  放行之前前前前前前===============");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("=============jwtFilter:doFilter  放行之后后后后后后===============");
    }

    /**
     *   结束时只调用一次
     *   默认方法，可以不用实现
     */
    @Override
    public void destroy() {
        //Filter.super.destroy();

        log.info("=============jwtFilter:destroy  结束掉你==============");
    }
}
