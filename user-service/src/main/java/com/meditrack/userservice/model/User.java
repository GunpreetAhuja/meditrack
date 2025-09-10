package com.meditrack.userservice.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "mt_users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mt_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();
    // getters/setters omitted for brevity
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getUsername(){return username;} public void setUsername(String u){this.username=u;}
    public String getPassword(){return password;} public void setPassword(String p){this.password=p;}
    public Set<String> getRoles(){return roles;} public void setRoles(Set<String> r){this.roles=r;}
}
