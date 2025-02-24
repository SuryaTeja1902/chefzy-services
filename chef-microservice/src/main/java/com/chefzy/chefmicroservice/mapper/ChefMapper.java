package com.chefzy.chefmicroservice.mapper;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.dto.ChefResponseDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ChefMapper {

    /**
     * Maps a Chef entity to a ChefResponseDTO.
     *
     * @param chef the Chef entity to map.
     * @return the mapped ChefResponseDTO.
     */
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

    /**
     * Maps a ChefDTO to a Chef entity.
     *
     * @param chefDTO the ChefDTO to map.
     * @return the mapped Chef entity.
     * @throws JsonProcessingException if there is an error processing JSON.
     */
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

    /**
     * Updates an existing Chef entity from a ChefDTO.
     *
     * @param existingChef the existing Chef entity to update.
     * @param chefDTO the ChefDTO containing updated details.
     */
    public static void updateChefFromDTO(Chef existingChef, ChefDTO chefDTO) {
        if (chefDTO.getName() != null) {
            existingChef.setName(chefDTO.getName());
        }
        if (chefDTO.getEmail() != null) {
            existingChef.setEmail(chefDTO.getEmail());
        }
        if (chefDTO.getPhone() != null) {
            existingChef.setPhone(chefDTO.getPhone());
        }
        if (chefDTO.isAvailable() != existingChef.isAvailable()) {
            existingChef.setAvailable(chefDTO.isAvailable());
        }
        if (chefDTO.getExperience() != 0) {
            existingChef.setExperience(chefDTO.getExperience());
        }
        if (chefDTO.getCuisines() != null) {
            existingChef.setCuisines(chefDTO.getCuisines());
        }
        if (chefDTO.getLanguages() != null) {
            existingChef.setLanguages(chefDTO.getLanguages());
        }
        if (chefDTO.getPricing() != 0) {
            existingChef.setPricing(chefDTO.getPricing());
        }
        if (chefDTO.getRating() != 0) {
            existingChef.setRating(chefDTO.getRating());
        }
    }
}