package com.example.project.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.project.Model.ApplicationUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  String jwtSecret;
  public String getJWTToken(String username){
    return Jwts.builder()
    .signWith(SignatureAlgorithm.HS256, jwtSecret)
    .setSubject(username)
    .compact();
  }

  public String extractUsername(String token){
    return extractClaim(token,Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
  } 

}