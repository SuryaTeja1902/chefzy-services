package com.chefzy.cateringmicroservice.mapper;

import com.chefzy.cateringmicroservice.dto.CatererDTO;
import com.chefzy.cateringmicroservice.dto.CatererResponseDTO;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CatererMapper {
    public static CatererResponseDTO mapToCatererResponseDTO(Caterer caterer) {

        CatererResponseDTO catererResponseDTO = new CatererResponseDTO();

        catererResponseDTO.setId(caterer.getId());
        catererResponseDTO.setName(caterer.getName());
        catererResponseDTO.setEmail(caterer.getEmail());
        catererResponseDTO.setPhone(caterer.getPhone());
        catererResponseDTO.setAvailable(caterer.isAvailable());
        catererResponseDTO.setRating(caterer.getRating());
        catererResponseDTO.setPricingPerPlate(caterer.getPricingPerPlate());
        catererResponseDTO.setCuisineType(caterer.getCuisineType());
        catererResponseDTO.setMenu(caterer.getMenu());
        catererResponseDTO.setMaxAccommodation((caterer.getMaxAccommodation()));

        return catererResponseDTO;
    }

    public static Caterer mapToCaterer(CatererDTO catererDTO) throws JsonProcessingException {

        Caterer caterer = new Caterer();

        caterer.setName(catererDTO.getName());
        caterer.setEmail(catererDTO.getEmail());
        caterer.setPhone(catererDTO.getPhone());
        caterer.setAvailable(catererDTO.isAvailable());
        caterer.setRating(catererDTO.getRating());
        caterer.setPricingPerPlate(catererDTO.getPricingPerPlate());
        caterer.setCuisineType(catererDTO.getCuisineType());
        caterer.setMenu(catererDTO.getMenu());
        caterer.setMaxAccommodation((catererDTO.getMaxAccommodation()));

        return caterer;
    }

    public static void updateCatererFromDTO(Caterer existingCaterer, CatererDTO catererDTO) throws JsonProcessingException {

        if(catererDTO.getName() != null)
        {
            existingCaterer.setName(catererDTO.getName());
        }
        if(catererDTO.getEmail() != null)
        {
            existingCaterer.setEmail(catererDTO.getEmail());
        }
        if(catererDTO.getPhone() != null)
        {
            existingCaterer.setPhone(catererDTO.getPhone());
        }
        if (catererDTO.isAvailable() != existingCaterer.isAvailable())
        {
            existingCaterer.setAvailable(catererDTO.isAvailable());
        }
        if(catererDTO.getMenu() != null)
        {
            existingCaterer.setMenu(catererDTO.getMenu());
        }
        if(catererDTO.getCuisineType() != null)
        {
            existingCaterer.setCuisineType(catererDTO.getCuisineType());
        }
        if(catererDTO.getEventType() != null)
        {
            existingCaterer.setEventType(catererDTO.getEventType());
        }
        if (catererDTO.getMaxAccommodation() != null)
        {
            existingCaterer.setMaxAccommodation(catererDTO.getMaxAccommodation());
        }
        if(catererDTO.getPricingPerPlate() != 0)
        {
            existingCaterer.setPricingPerPlate(catererDTO.getPricingPerPlate());
        }
        if(catererDTO.getRating() != 0)
        {
            existingCaterer.setRating(catererDTO.getRating());
        }
    }
}
