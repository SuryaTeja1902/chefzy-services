package com.chefzy.cateringmicroservice.api;

import com.chefzy.cateringmicroservice.dto.CatererDTO;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface CateringAPI {
    @GetMapping("/")
    List<Caterer> getAllCaterers();

    @GetMapping("/{id}")
    Optional<Caterer> getCatererById(@PathVariable("id") long id) throws ValidationException;

    @GetMapping("/event/{eventType}")
    List<Caterer> getCatererByEvent(@PathVariable("eventType") String eventType);


    @PostMapping("/")
    Caterer createCaterer(@RequestBody CatererDTO catererDTO) throws ValidationException;
    @PutMapping("/{id}")
    Caterer updateCaterer(@PathVariable("id") long id, @RequestBody CatererDTO catererDTO) throws JsonProcessingException;

    @DeleteMapping("/{id}")
    void deleteCaterer(@PathVariable("id") long id);
}
