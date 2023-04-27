package com.example.project.security;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.service.ApplicationUserService;
import com.example.project.service.UserAuthService;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

  @Autowired
  JwtUtil jwtUtil;

  @Autowired 
  ApplicationUserRepository applicationUserRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
   HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

        if(request.getHeader("Authorization") != null){
          String token = request.getHeader("Authorization").substring(7);
          String username = jwtUtil.extractUsername(token);
          System.out.println("Username --- "+username);
          ApplicationUser user =  applicationUserRepository.findById(username).get();

          UserDetails userDetails = new User(user.getUser_name(), user.getPassword(), new ArrayList<>());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
      usernamePasswordAuthenticationToken
              .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
  }



}