package com.example.mdc.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserMdcInterceptor implements HandlerInterceptor {
    public static final String USER_ID = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uid = request.getHeader("X-User-Id");
        if (uid != null && !uid.isBlank()) {
            MDC.put(USER_ID, uid);
        }
        return true;
    }
}
