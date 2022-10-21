package com.example.chatrebuild.tool;

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
public class RoleFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    private final String[] publicURI = {"/main", "/login", "/register", "/activate", "/createDish", "/dishes"};

    private final String[] customerURI = {"/customer"};

    private final String[] adminURI = {"/secured"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (Arrays.asList(publicURI).contains(request.getRequestURI()))
        {
            filterChain.doFilter(request, response);
        }
        else {
            if (request.getHeader("Authorization")!= null) {
                var token = request.getHeader("Authorization").substring(7);
                var login = request.getParameter("login");
                var uri = request.getRequestURI();
                var role = jwtProvider.checkRoleJWT(token, login);

                if (role.equals("CUSTOMER")){
                    if (Arrays.asList(customerURI).contains(uri))
                        filterChain.doFilter(request, response);
                    else
                        response.sendError(403);
                }
                if (role.equals("WORKER")){
                    if (Arrays.asList(customerURI).contains(uri))
                        filterChain.doFilter(request, response);
                    else
                        response.sendError(403);
                }
                if (role.equals("COOKER")){
                    if (Arrays.asList(customerURI).contains(uri))
                        filterChain.doFilter(request, response);
                    else
                        response.sendError(403);
                }
                if (role.equals("MANAGER")){
                    if (Arrays.asList(customerURI).contains(uri))
                        filterChain.doFilter(request, response);
                    else
                        response.sendError(403);
                }
                if (role.equals("ADMIN")){
                    if (Arrays.asList(adminURI).contains(uri))
                        filterChain.doFilter(request, response);
                    else
                        response.sendError(403);
                }
            }
            else {
                response.sendError(403);
            }
        }
    }

    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }
}
