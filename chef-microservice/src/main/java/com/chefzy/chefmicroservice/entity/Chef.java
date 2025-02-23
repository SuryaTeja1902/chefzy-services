package com.chefzy.chefmicroservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chefs")
@Data
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int experience;
    private String cuisines;
    private String languages;
    private long pricing;
    private double rating;
    private boolean isAvailable;



    public Chef() {

    }
}
