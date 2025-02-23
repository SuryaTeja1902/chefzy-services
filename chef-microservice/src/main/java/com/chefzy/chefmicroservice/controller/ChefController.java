package com.chefzy.chefmicroservice.controller;

import com.chefzy.chefmicroservice.api.ChefAPI;
import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.dto.ChefResponseDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.mapper.ChefMapper;
import com.chefzy.chefmicroservice.service.ChefService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chefs")
@Slf4j
public class ChefController implements ChefAPI {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @Override
    public List<Chef> getAllChefs() {
        log.info("Received request to get all chefs");
        return chefService.getAllChefs();
    }

    @Override
    public Optional<Optional<Chef>> getChefById(long id) throws ValidationException {
        log.info("Received request to get chef info with ID - {}",id);
        try {
            chefService.getChefById(id);
        }
        catch (ValidationException e) {
            log.error(e.getMessage());
            return  Optional.empty();
        }
        return Optional.ofNullable(chefService.getChefById(id));
    }

    @Override
    public ResponseEntity<ChefResponseDTO> createChef(ChefDTO chefDTO) throws ValidationException {
        Chef chef = chefService.createChef(chefDTO);
        log.info("Chef created with  ID - {}",chef.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChefMapper.mapToChefResponseDTO(chef));
    }

    @Override
    public ResponseEntity<String> updateChef(long id, ChefDTO chefDTO) {
        log.info("Received request to update chef with ID - {}",id);
        try {
            chefService.updateChef(id, chefDTO);
            log.info("Successfully updated chef with  ID - {}",id);
        }
        catch (ValidationException ex) {
            log.error("Chef with ID - {} NOT found",id);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<String> deleteChef(long id) {
        log.info("Received request to delete chef");
        try {
            chefService.deleteChef(id);
        }
        catch (ValidationException ex) {
            log.error("Chef with ID - {} NOT found",id);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}