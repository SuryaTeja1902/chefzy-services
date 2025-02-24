package com.chefzy.cateringmicroservice.dto;

import lombok.Data;

@Data
public class CatererResponseDTO {

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
}