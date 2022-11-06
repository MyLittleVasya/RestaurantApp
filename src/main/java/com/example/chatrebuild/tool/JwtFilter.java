package com.example.chatrebuild.tool;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    private final String[] publicURI = {"/main", "/login", "/register", "/activate", "/createDish", "/dishes", "/createOrder", "/showOrders", "/deleteOrder"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Arrays.asList(publicURI).contains(request.getRequestURI()))
        {
            filterChain.doFilter(request, response);
        }
        else {
            if (request.getHeader("Authorization")!= null && authorizeRequest(request, response)) {
                filterChain.doFilter(request, response);
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
        catch (JwtException exception) {
            System.out.println(exception);
            return false;
        }
        return true;
    }

    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
}
