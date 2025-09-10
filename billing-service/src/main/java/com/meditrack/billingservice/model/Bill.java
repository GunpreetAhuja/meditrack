package com.meditrack.billingservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mt_bills")
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long appointmentId;
    private Double amount;
    private String status;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Long getAppointmentId(){return appointmentId;} public void setAppointmentId(Long a){this.appointmentId=a;}
    public Double getAmount(){return amount;} public void setAmount(Double amt){this.amount=amt;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
}
