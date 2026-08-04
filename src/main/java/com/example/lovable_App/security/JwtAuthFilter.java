package com.example.lovable_App.security;

import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtil authUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    log.info("incoming request:{}", request.getRequestURI());
    final String requestHeaderToken=request.getHeader("Authorization");
    if (requestHeaderToken != null || !requestHeaderToken.startsWith("Bearer ")) {
        filterChain.doFilter(request,response);
        return;

    }

    //Authorization= Bearer sdsd5f4sdf4sd4fsdf4s5
    String token=requestHeaderToken.split("Bearer ")[1];

        JwtUserPrinciple user=authUtil.verifyAccessToken(token);//we authenticated the user
        // This is authentication
        if (user!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken
                    (user,null,user.authorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request,response);
    }
}
