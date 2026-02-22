package com.ibm.interceptor;

import com.ibm.pojo.Result;
import com.ibm.utils.GetJwtUtil;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("*************** TokenInterceptor preHandle  Start***************");

        // 1. get URL 【取得路径】
        String uri = request.getRequestURI().toString();

        // 2. 【不判断令牌的请求】
        if (uri.contains("/loginById") || uri.contains("/login")) {

            return true;
        }

        //3. 【取得令牌】 request里有没有‘token’？？自动带进来的，还是人为放进
        String jwtToken = request.getHeader("token");
        log.info("=========Token is : {} ==============" , jwtToken);

        //4. 【判断令牌空，或null】
        if (jwtToken == null || jwtToken.equals("")) {
            log.info("============ token is null or empty ===============");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token is null or empty");
            return false;
        }
        //5. 【判断令牌的有效性：篡改，或过期】
        try{
            GetJwtUtil.paarseJwt(jwtToken);
        } catch (Exception e) {
            log.info("============ token parse have error!!!! : {}", e.getMessage());
            Result loginError = Result.error("token parse have error!!!");

            String s = JSONObject.quote(loginError.toString());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
            return false;
        }

        log.info("============ token parse success!!!! : {}", jwtToken);
        log.info("*************** TokenInterceptor preHandle  End***************");
        return true;
    }
}
