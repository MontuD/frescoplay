package com.example.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project.Model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,String>{

  @Query(nativeQuery = true,
  value = "SELECT * from Apppointment where patientId = ?1")
  public Optional<List<Appointment>> listOfAppointmentByPatientId(String patientId );



}
