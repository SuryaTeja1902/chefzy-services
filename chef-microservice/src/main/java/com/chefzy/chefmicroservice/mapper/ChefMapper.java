package com.chefzy.chefmicroservice.mapper;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.dto.ChefResponseDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ChefMapper {

     public static ChefResponseDTO mapToChefResponseDTO(Chef chef) {

         ChefResponseDTO chefResponseDTO = new ChefResponseDTO();

         chefResponseDTO.setId(chef.getId());
         chefResponseDTO.setName(chef.getName());
         chefResponseDTO.setEmail(chef.getEmail());
         chefResponseDTO.setPhone(chef.getPhone());
         chefResponseDTO.setAvailable(chef.isAvailable());
         chefResponseDTO.setRating(chef.getRating());
         chefResponseDTO.setPricing(chef.getPricing());
         chefResponseDTO.setExperience(chef.getExperience());
         chefResponseDTO.setCuisines(chef.getCuisines());
         chefResponseDTO.setLanguages(chef.getLanguages());

         return chefResponseDTO;
     }

     public static Chef mapToChef(ChefDTO chefDTO) throws JsonProcessingException {

         Chef chef = new Chef();

         chef.setName(chefDTO.getName());
         chef.setEmail(chefDTO.getEmail());
         chef.setPhone(chefDTO.getPhone());
         chef.setAvailable(chefDTO.isAvailable());
         chef.setRating(chefDTO.getRating());
         chef.setPricing(chefDTO.getPricing());
         chef.setExperience(chefDTO.getExperience());
         chef.setCuisines(chefDTO.getCuisines());
         chef.setLanguages(chefDTO.getLanguages());

         return chef;
     }
}
