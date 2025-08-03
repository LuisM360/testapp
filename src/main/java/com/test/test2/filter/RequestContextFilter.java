package com.test.test2.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component("myRequestContextFilter")
public class RequestContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestId = UUID.randomUUID().toString();
        String userId = request.getHeader("X-User-ID");
        if (userId == null || userId.isEmpty()) {
            userId = "anonymous-" + (System.currentTimeMillis() % 1000);
        }

        try {
            MDC.put("requestId", requestId);
            MDC.put("userId", userId);
            MDC.put("clientIp", request.getRemoteAddr());
            MDC.put("requestUri", request.getRequestURI());

            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}