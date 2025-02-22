package com.chefzy.chefmicroservice.dto;

import lombok.Data;

@Data
public class ChefResponseDTO {
    private long id;
    private String name;
    private String email;
    private String phone;
    private int experience;
    private String cuisines;
    private String languages;
    private long pricing;
    private double rating;
    private boolean isAvailable;
}
