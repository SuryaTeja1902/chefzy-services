package com.chefzy.chefmicroservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chefs")
@Data
@NoArgsConstructor
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chef_id")
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int experience;
    private String cuisines;
    private String languages;
    private long pricing;
    private double rating;
    @Column(name = "is_available")
    private boolean isAvailable;
}
