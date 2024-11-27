package com.scm.app.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        
        // Exclude login endpoint
        if ("/login".equals(requestUri)) {
            return true;
        }

        // Check for accessToken in the header
        String accessToken = request.getHeader("accessToken");
//        if (accessToken == null || accessToken.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Missing or invalid accessToken");
//            return false;
//        }

        return true; // Proceed with the request
    }
}
