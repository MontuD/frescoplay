package com.example.project.controller;

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

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;
@RestController
@RequestMapping("/patients")
public class PatientController {

  @Autowired 
  PatientService patientService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody Patient patient){
    return ResponseEntity.ok(this.patientService.register(patient));
  }


  @GetMapping("/list")
  public ResponseEntity  listOfPatient(){
    return ResponseEntity.ok(this.patientService.listOfPatient());
  }


  @GetMapping("/view/{patientId}")
  public ResponseEntity PatientByPatientId(@PathVariable String patientId){
   return ResponseEntity.ok(this.patientService.PatientByPatientId(patientId));
  }


  @DeleteMapping("/delete/{patientId}")
  public void deletePatientById(@PathVariable String patientId){
    this.patientService.PatientByPatientId(patientId);
  }
	
}
