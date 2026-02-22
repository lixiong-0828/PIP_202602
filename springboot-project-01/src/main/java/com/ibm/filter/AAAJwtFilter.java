package com.ibm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter (urlPatterns = "/*")  // 拦截所有请求  //Java Web的原生组件。。想用在启动类里加注解
public class AAAJwtFilter implements Filter {
    /**
     *   【AAAJwtFilter】的目的是为了证明，【存在过滤器链】时的执行顺序：
     *    过滤器类名的排序二执行。
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);

        log.info("=============AAAJwtFilter:init 初始化you了===============");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("=============AAAJwtFilter:doFilter  拦截到你了===============");


        log.info("=============AAAJwtFilter:doFilter  放行之前前前前前前===============");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("=============AAAJwtFilter:doFilter  放行之后后后后后后===============");
    }

    /**
     *   结束时只调用一次
     *   默认方法，可以不用实现
     */
    @Override
    public void destroy() {
        //Filter.super.destroy();

        log.info("=============AAAJwtFilter:destroy  结束掉你==============");
    }
}
