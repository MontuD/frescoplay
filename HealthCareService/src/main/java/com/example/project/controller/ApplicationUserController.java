package com.example.project.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.ApplicationUserService;

@RestController
public class ApplicationUserController {

  @Autowired
  ApplicationUserService userService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody ApplicationUser applicationUser){
    return ResponseEntity.ok(this.userService.register(applicationUser));
  }

  @PostMapping("/signin")
  public ResponseEntity signin(@RequestBody ApplicationUser applicationUser){
    return ResponseEntity.ok(this.userService.signin(applicationUser));
  }
	
}
