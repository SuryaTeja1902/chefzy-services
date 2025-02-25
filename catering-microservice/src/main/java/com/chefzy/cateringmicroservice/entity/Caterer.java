package com.chefzy.cateringmicroservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "caterers")
@Data
public class Caterer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String menu;
    private String cuisineType;
    private String eventType;
    private Long maxAccommodation;
    private int pricingPerPlate;
    private double rating;
    private boolean isAvailable;

    public Caterer() {}
}
