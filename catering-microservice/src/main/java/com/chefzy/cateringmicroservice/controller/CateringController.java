package com.chefzy.cateringmicroservice.controller;


import com.chefzy.cateringmicroservice.api.CateringAPI;
import com.chefzy.cateringmicroservice.dto.CatererDTO;
import com.chefzy.cateringmicroservice.dto.CatererResponseDTO;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.chefzy.cateringmicroservice.mapper.CatererMapper;
import com.chefzy.cateringmicroservice.service.CateringService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Optional<Caterer> getCatererById(long id) {
        log.info("Received request to get caterer info with ID - {}", id);
        return cateringService.getCatererById(id);
    }


    @Override
    public List<Caterer> getCatererByEvent(String eventType)
    {
        return cateringService.getCatererByEvent(eventType);
    }

    @Override
    public ResponseEntity<CatererResponseDTO> createCaterer(CatererDTO catererDTO) throws ValidationException {
        Caterer caterer = cateringService.createCaterer(catererDTO);
        log.info("Caterer created with  ID - {}", caterer.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(CatererMapper.mapToCatererResponseDTO(caterer));

    }
    @Override
    public ResponseEntity<String> updateCaterer(long id, CatererDTO catererDTO)  {
        log.info("Received request to update caterer with ID - {}", id);
        try {
            cateringService.updateCaterer(id, catererDTO);
            log.info("Successfully updated caterer with ID - {}", id);
        } catch (ValidationException ex) {
            log.error("Caterer with ID - {} NOT found", id);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }
    @Override
    public void deleteCaterer(long id) {
        cateringService.deleteCaterer(id);
    }
}
