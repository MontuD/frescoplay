package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Optional;

import org.json.simple.JSONObject;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;



@Service
public class ApplicationUserService {

  @Autowired
  ApplicationUserRepository applicationUserRepository;

  @Autowired 
  JwtUtil jwtUtil;

  public HashMap<String,Object> register(ApplicationUser applicationUser){
    HashMap<String,Object> hashmap = new HashMap<>();
    if(applicationUser.getPassword().isEmpty()  || 
    applicationUser.getLocation().isEmpty() ||
    applicationUser.getUser_email().isEmpty() ||
     applicationUser.getUser_mobile().isEmpty() ||
     applicationUser.getUser_name().isEmpty()){
      hashmap.put("message", "Password or username policy failed");
      return hashmap;   
    }else{
      this.applicationUserRepository.save(applicationUser);
      hashmap.put("message", "'Registration sucessful'");
    }
    return hashmap;  
  }
  
  
  public HashMap<String,Object> signin(ApplicationUser applicationUser){
    HashMap<String,Object> result = new HashMap<>();
    if(applicationUser.getUser_name().isEmpty() || applicationUser.getPassword().isEmpty()){
      result.put("message", "Username or Password is Incorrect.");
    }else{
      Optional<ApplicationUser> user = this.applicationUserRepository.findById(applicationUser.getUser_name());
      if(user.isPresent()){
          if(user.get().getPassword().equals(applicationUser.getPassword())){
            String token = jwtUtil.getJWTToken(applicationUser.getUser_name());
            result.put("message", "Authentication successful!");
            result.put("token", token);
            result.put("id", applicationUser.getUser_name());
          }
          else{
            result.put("message", "Username or Password is Incorrect.");
          }
      }
    }

    return result;

  }

}

