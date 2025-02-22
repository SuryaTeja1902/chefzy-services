package com.chefzy.chefmicroservice.api;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface ChefAPI{

    @GetMapping("/")
    public List<Chef> getAllChefs();

    @GetMapping("/{id}")
    public Optional<Optional<Chef>> getChefById(@PathVariable("id") long id);

    @PostMapping("/")
    public Chef createChef(@RequestBody ChefDTO chefDTO);


    @PutMapping("/{id}")
    public Chef updateChef(@PathVariable("id") long id, ChefDTO chefDTO);


    @DeleteMapping("/{id}")
    public String deleteChef(@PathVariable("id") long id);
}
