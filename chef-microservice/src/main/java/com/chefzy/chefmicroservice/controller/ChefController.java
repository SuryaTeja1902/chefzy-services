package com.chefzy.chefmicroservice.controller;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.service.ChefService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chefs")
@Slf4j
public class ChefController {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping("/")
    public List<Chef> getAllChefs() {
        return chefService.getAllChefs();
    }

    @GetMapping("/{id}")
    public Optional<Optional<Chef>> getChefById(@PathVariable("id") long id) {
        return Optional.ofNullable(chefService.getChefById(id));
    }

    @PostMapping("/")
    public Chef createChef(@RequestBody ChefDTO chefDTO)
    {
        log.info("Received new request to add chef");
        return chefService.createChef(chefDTO);
    }

    @PutMapping("/{id}")
    public Chef updateChef(@PathVariable("id") long id, ChefDTO chefDTO)
    {
        log.info("Received request to update chef");
        return chefService.updateChef(id, chefDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteChef(@PathVariable("id") long id)
    {
        return chefService.deleteChef(id);
    }
}