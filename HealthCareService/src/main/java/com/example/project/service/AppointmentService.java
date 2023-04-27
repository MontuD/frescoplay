package com.example.project.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Model.Appointment;
import com.example.project.Model.Patient;
import com.example.project.repository.AppointmentRepository;
import com.example.project.repository.PatientRepository;

@Service
public class AppointmentService {
	
  @Autowired
  AppointmentRepository appointmentRepository;

  public HashMap<String,Object> register(Appointment appointment){
    HashMap<String,Object> result = new HashMap<>();
    if(appointment != null){
      this.appointmentRepository.save(appointment);
      result.put("message", "Booking successful");
    }else{
      result.put("message", "Booking failure");
    }
    return result;
  }

  
  

  public List<Appointment> listOfAppointment(){
    return this.appointmentRepository.findAll();
  }

  public Appointment appointmentByAppointmentId(String appointmentId){
    Optional<Appointment> appointment = this.appointmentRepository.findById(appointmentId);
    if(appointment.isPresent()){
      return  appointment.get();
    } 
    return null;
  }


  public List<Appointment> listOfAppointmentByPatientId(String patientId){
    Optional<List<Appointment>> appointmentOptional = this.appointmentRepository.listOfAppointmentByPatientId(patientId);
    if(appointmentOptional.isPresent()){
      return appointmentOptional.get();
    }
    return null;

  }

  public void deleteAppointmentById(String appointmentId){
    this.appointmentRepository.deleteById(appointmentId);
  }

  




}
