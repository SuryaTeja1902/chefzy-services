package com.chefzy.chefmicroservice.entity;


import com.chefzy.chefmicroservice.dto.ChefDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "chefs")
@Data
public class Chef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private int experience;
    private String cuisines;
    private String languages;
    private long pricing;
    private double rating;
    private boolean availability;

    public Chef(ChefDTO chefDTO) {
        this.name = chefDTO.getName();
        this.email = chefDTO.getEmail();
        this.phone = chefDTO.getPhone();
        this.experience = chefDTO.getExperience();
        this.cuisines = chefDTO.getCuisines();
        this.languages = chefDTO.getLanguages();
        this.pricing = chefDTO.getPricing();
        this.rating = chefDTO.getRating();
        this.availability = chefDTO.isAvailability();

    }


    public Chef() {

    }
}
