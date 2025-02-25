package com.chefzy.chefmicroservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChefDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email
    private String email;

    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;

    private int experience;

    @NotEmpty(message = "Cuisines cannot be empty")
    private String cuisines;

    private String languages;
    private long pricing;
    private double rating;
    private boolean isAvailable;
}