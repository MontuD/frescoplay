package com.example.project.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;

@Service
public class PatientService {


  @Autowired
  PatientRepository patientRepository;


  public HashMap<String,Object> register(Patient patient){
    HashMap<String,Object> result = new HashMap<>();
    if(patient != null){
      this.patientRepository.save(patient);
      result.put("message", "Registraion successful");
    }else{
      result.put("message", "Registraion failure");
    }
    return result;
  }

  public List<Patient> listOfPatient(){
    return this.patientRepository.findAll();
  }

  public Patient PatientByPatientId(String PatientId){
    Optional<Patient> Patient = this.patientRepository.findById(PatientId);
    if(Patient.isPresent()){
      return  Patient.get();
    } 
    return null;
  }


  public void deletePatientById(String PatientId){
    this.patientRepository.deleteById(PatientId);
  }
}
