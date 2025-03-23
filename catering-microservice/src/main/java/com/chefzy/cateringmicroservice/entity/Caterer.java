package com.chefzy.cateringmicroservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "caterers")
@Data
public class Caterer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "caterer_id")
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String menu;

    @Column(name = "cuisine_type")
    private String cuisineType;
    @Column(name = "event_type")
    private String eventType;
    @Column(name = "max_accommodation")
    private Long maxAccommodation;
    @Column(name = "pricing_per_plate")
    private int pricingPerPlate;

    private double rating;

    @Column(name = "is_available")
    private boolean isAvailable;

}
