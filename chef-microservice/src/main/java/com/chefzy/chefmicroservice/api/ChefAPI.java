package com.chefzy.chefmicroservice.api;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.dto.ChefResponseDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ChefAPI{

    @GetMapping("/")
    List<Chef> getAllChefs();

    @GetMapping("/{id}")
    Optional<Optional<Chef>> getChefById(@PathVariable("id") long id);

    @PostMapping("/")
    ResponseEntity<ChefResponseDTO> createChef(@RequestBody ChefDTO chefDTO) throws ValidationException, JsonProcessingException;

    @PutMapping("/{id}")
    ResponseEntity<Void> updateChef(@PathVariable("id") long id, ChefDTO chefDTO);

    @DeleteMapping("/{id}")
    String deleteChef(@PathVariable("id") long id);
}