package com.chefzy.cateringmicroservice.controller;


import com.chefzy.cateringmicroservice.api.CateringAPI;
import com.chefzy.cateringmicroservice.dto.CatererDTO;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.chefzy.cateringmicroservice.service.CateringService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/caterers")
@Slf4j
public class CateringController implements CateringAPI {

    private final CateringService cateringService;

    public CateringController(CateringService cateringService) {
        this.cateringService = cateringService;
    }
    @Override
    public List<Caterer> getAllCaterers()
    {
        log.info("Received request to get all caterers");
        return cateringService.getAllCaterers();
    }
    @Override
    public Optional<Caterer> getCatererById(long id)
    {
        log.info("Received request to get caterer info with ID - {}",id);
        try {
            cateringService.getCatererById(id);
        }
        catch (ValidationException e) {
            log.error(e.getMessage());
            return  Optional.empty();
        }
        return Optional.ofNullable(cateringService.getCatererById(id););



        return
    }
    @Override
    public List<Caterer> getCatererByEvent(String eventType)
    {
        return cateringService.getCatererByEvent(eventType);
    }

    @Override
    public Caterer createCaterer(CatererDTO catererDTO) throws ValidationException {
        return cateringService.createCaterer(catererDTO);
    }
    @Override
    public Caterer updateCaterer(long id, CatererDTO catererDTO) throws JsonProcessingException {
        return cateringService.updateCaterer(id,catererDTO);
    }
    @Override
    public void deleteCaterer(long id) {
        cateringService.deleteCaterer(id);
    }
}
