package com.chefzy.chefmicroservice.controller;

import com.chefzy.chefmicroservice.api.ChefAPI;
import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.dto.ChefResponseDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.mapper.ChefMapper;
import com.chefzy.chefmicroservice.service.ChefService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return chefService.getAllChefs();
    }

    @Override
    public Optional<Optional<Chef>> getChefById(@PathVariable("id") long id) {
        return Optional.ofNullable(chefService.getChefById(id));
    }

    @Override
    public ResponseEntity<ChefResponseDTO> createChef(@RequestBody ChefDTO chefDTO) throws ValidationException {
        Chef chef = chefService.createChef(chefDTO);
        log.info("Chef created with  ID - {}",chef.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChefMapper.mapToChefResponseDTO(chef));
    }

    @Override
    public ResponseEntity<Void> updateChef(@PathVariable("id") long id, @RequestBody ChefDTO chefDTO) throws JsonProcessingException {
        log.info("Received request to update chef");
        chefService.updateChef(id, chefDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public String deleteChef(@PathVariable("id") long id)
    {
        log.info("Received request to delete chef");
        return chefService.deleteChef(id);
    }
}