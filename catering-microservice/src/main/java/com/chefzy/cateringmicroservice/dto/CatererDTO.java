package com.chefzy.cateringmicroservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CatererDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;

    @NotEmpty(message = "Menu cannot be empty")
    private String menu;

    private String cuisineType;

    @NotEmpty(message = "Event type cannot be empty")
    private String eventType;

    private Long maxAccommodation;
    private int pricingPerPlate;
    private double rating;
    private boolean isAvailable;
}