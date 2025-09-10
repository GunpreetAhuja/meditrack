package com.meditrack.appointmentservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mt_appointments")
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime time;
    private String status;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getPatientId(){return patientId;} public void setPatientId(Long p){this.patientId=p;}
    public Long getDoctorId(){return doctorId;} public void setDoctorId(Long d){this.doctorId=d;}
    public LocalDateTime getTime(){return time;} public void setTime(LocalDateTime t){this.time=t;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
