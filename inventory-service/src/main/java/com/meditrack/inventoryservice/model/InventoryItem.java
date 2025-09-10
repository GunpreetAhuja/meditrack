package com.meditrack.inventoryservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mt_inventory")
public class InventoryItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer qty;
    // getters/setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public Integer getQty(){return qty;} public void setQty(Integer q){this.qty=q;}
}
