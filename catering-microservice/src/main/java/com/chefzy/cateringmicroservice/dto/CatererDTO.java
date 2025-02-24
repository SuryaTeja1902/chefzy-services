package com.chefzy.cateringmicroservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CatererDTO {

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
