package com.chefzy.chefmicroservice.api;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.dto.ChefResponseDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ChefAPI{

    @GetMapping("/")
    List<Chef> getAllChefs();

    @GetMapping("/{id}")
    ResponseEntity<Chef> getChefById(@PathVariable("id") long id) throws ValidationException;

    @PostMapping("/")
    ResponseEntity<ChefResponseDTO> createChef(@RequestBody @Validated ChefDTO chefDTO) throws ValidationException, JsonProcessingException;

    @PutMapping("/{id}")
    ResponseEntity<String> updateChef(@PathVariable("id") long id, @RequestBody @Validated ChefDTO chefDTO) throws JsonProcessingException, ValidationException;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteChef(@PathVariable("id") long id) throws ValidationException;
}