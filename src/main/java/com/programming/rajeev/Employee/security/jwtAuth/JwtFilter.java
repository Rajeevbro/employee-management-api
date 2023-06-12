package com.programming.rajeev.Employee.security.jwtAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal
            (HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain)
            throws ServletException, IOException {

       final String authHeader = request.getHeader("Authorization");
       final String jwtToken;
       final String userName;

       if(authHeader == null ||!authHeader.startsWith("Bearer ")  ){
           filterChain.doFilter(request,response);
           return;
       }

       jwtToken =authHeader.substring(7);
       userName = jwtService.extractUserName(jwtToken);


       if(userName !=null || SecurityContextHolder.getContext().getAuthentication() ==null){
           UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
           if(jwtService.isTokenValid(jwtToken,userDetails.getUsername()))
           {
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
           else {
               throw new ResponseStatusException(HttpStatus.NOT_FOUND,"UserName/ Password is incorrect");
           }


       }
       filterChain.doFilter(request,response);





    }
}
