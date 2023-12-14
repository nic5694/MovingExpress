package com.example.backend.config.security.filters;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseHeaderFilter implements Filter{
    @Override
    public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain
    ) throws IOException, ServletException {
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Headers", "content-type");
        httpResponse.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");


        chain.doFilter(request, response);
    }
}
