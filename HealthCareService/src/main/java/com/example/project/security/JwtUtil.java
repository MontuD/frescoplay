package com.example.project.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.project.Model.ApplicationUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {

  @Value("$(jwt.secret)")
  String jwtSecret;
  public String getJWTToken(String username){
    return Jwts.builder()
    .signWith(SignatureAlgorithm.HS256, jwtSecret)
    .setPayload(username)
    .compact();
  }

}