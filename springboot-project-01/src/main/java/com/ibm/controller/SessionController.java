package com.ibm.controller;

import com.ibm.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SessionController {

    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response) {

        response.addCookie(new Cookie("hello", "hello-cookie"));
        response.addCookie(new Cookie("study", "study-cookie"));
        return Result.success();
    }

    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info("===============cookie name: {} , cookie value {}", cookie.getName() , cookie.getValue());
            }
        }
        return Result.success();
    }

    @GetMapping("/s1")
    public Result session(HttpSession session) {
        log.info("session1111111111 hashCode: {}", session.hashCode());

        session.setAttribute("new-session", "lixiong-session");
        return Result.success();
    }

    @GetMapping("/s2")
    public Result session2(HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info("session2222222222222 hashCode: {}", session.hashCode());
        log.info("new-session : {}",session.getAttribute("new-session"));

        return Result.success(session.getAttribute("new-session"));
    }



}
