package com.example.demoBoot;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        /* Autenticacion */
        final String authHeader = request.getHeader("authorization");
        System.out.println("AuthHeader: " + authHeader); 
        System.out.println("Method: " + request.getMethod());

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("An exception occurred");
            }

            final String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            request.setAttribute("blog", servletRequest.getParameter("id"));
        }
        /* Fin autenticacion */
        
        filterChain.doFilter(request, response);
    }

}

