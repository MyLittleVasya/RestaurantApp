package com.example.chatrebuild.tool;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends GenericFilterBean {

    @Autowired
    private JwtProvider jwtProvider;

    private final String[] publicURI = {"/main", "/login", "/register", "/activate"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        if (Arrays.asList(publicURI).contains(request.getRequestURI()))
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            if (request.getHeader("Authorization")!= null && authorizeRequest(request, response)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                response.sendError(403);
            }
        }
    }

    public boolean authorizeRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            jwtProvider.validateToken(
                    request.getHeader("Authorization").substring(7),
                    request.getParameter("login")
                    );
        }
        catch (JwtException exception) {return false;}
        return true;
    }

}
