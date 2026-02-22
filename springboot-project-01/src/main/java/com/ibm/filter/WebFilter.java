package com.ibm.filter;

import com.ibm.pojo.Result;
import com.ibm.utils.GetJwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import java.io.IOException;

@Slf4j
@jakarta.servlet.annotation.WebFilter(urlPatterns = "/*")
public class WebFilter implements Filter {
//    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("**************** 过滤器 初始化 **************** ");
    }

//    @Override
     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("**************** 过滤器 过滤每个方法 **************** ");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1. get URL 【取得路径】
//        String uri = request.getRequestURI().toString();
//        log.info("--------------uri: {}", uri);
//        // 2. 【不判断令牌的请求】
//        if (uri.contains("/loginById") || uri.contains("/login")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //3. 【取得令牌】 request里有没有‘token’？？自动带进来的，还是人为放进
//        String jwtToken = request.getHeader("token");
//        log.info("=========WebFilter jwt : {} ==============" , jwtToken);
//
//        //4. 【判断令牌空，或null】
//        if (jwtToken == null || jwtToken.equals("")) {
//            log.info("============ token is null or empty ===============");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token is null or empty");
//            return;
//        }
//        //5. 【判断令牌的有效性：篡改，或过期】
//        try{
//            GetJwtUtil.paarseJwt(jwtToken);
//        } catch (Exception e) {
//            log.info("============ token parse have error!!!! : {}", e.getMessage());
//            Result loginError = Result.error("token parse have error!!!");
//
//            String s = JSONObject.quote(loginError.toString());
//            servletResponse.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(s);
//            return;
//        }

//        log.info("============ token parse success!!!! : {}", jwtToken);
        filterChain.doFilter(servletRequest, servletResponse);

    }

//    @Override
    public void destroy() {
        log.info("**************** 过滤器 结束 **************** ");
    }

}
