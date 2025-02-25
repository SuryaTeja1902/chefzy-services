package com.chefzy.cateringmicroservice.api;

import com.chefzy.cateringmicroservice.dto.CatererDTO;
import com.chefzy.cateringmicroservice.dto.CatererResponseDTO;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CateringAPI {
    @GetMapping("/")
    List<Caterer> getAllCaterers();

    @GetMapping("/{id}")
    ResponseEntity<Caterer> getCatererById(@PathVariable("id") long id) throws ValidationException;

    @GetMapping("/event/{eventType}")
    ResponseEntity<List<Caterer>> getCatererByEvent(@PathVariable("eventType") String eventType);

    @PostMapping("/")
    ResponseEntity<CatererResponseDTO> createCaterer(@RequestBody @Validated CatererDTO catererDTO) throws ValidationException;

    @PutMapping("/{id}")
    ResponseEntity<String> updateCaterer(@PathVariable("id") long id, @RequestBody @Validated CatererDTO catererDTO) throws JsonProcessingException, ValidationException;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCaterer(@PathVariable("id") long id);
}
