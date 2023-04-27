package com.example.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Appointment;
import com.example.project.service.AppointmentService;

@RestController
@RequestMapping("/appointment/")
public class AppointmentController {

  @Autowired
  AppointmentService appointmentService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody Appointment Appointment){
    return ResponseEntity.ok(this.appointmentService.register(Appointment));
  }


  @GetMapping("/list/")
  public ResponseEntity  listOfAppointment(){
    return ResponseEntity.ok(this.appointmentService.listOfAppointment());
  }


  @GetMapping("/view/{AppointmentId}")
  public ResponseEntity AppointmentByAppointmentId(@PathVariable String AppointmentId){
   return ResponseEntity.ok(this.appointmentService.appointmentByAppointmentId(AppointmentId));
  }

  @GetMapping("/view/list/{patientId}")
  public ResponseEntity listOfAppointmentByPatientId(@PathVariable String patientId){
   return ResponseEntity.ok(this.appointmentService.listOfAppointmentByPatientId(patientId));
  }


  @DeleteMapping("/delete/{AppointmentId}")
  public void deleteAppointmentById(@PathVariable String AppointmentId){
    this.appointmentService.deleteAppointmentById(AppointmentId);
  }



	
	
}
