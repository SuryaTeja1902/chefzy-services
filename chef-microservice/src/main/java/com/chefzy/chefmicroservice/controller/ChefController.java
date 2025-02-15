package com.chefzy.chefmicroservice.controller;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chefs")
public class ChefController {

    @Autowired
    ChefService chefService;

    @GetMapping("/")
    public List<Chef> getAllChefs() {
        return chefService.getAllChefs();

    }

    @GetMapping("/{id}")
    public Optional<Chef> getChefById(@PathVariable("id") long id) {
        return chefService.getChefById(id);
    }


    @PostMapping("/")
    public Chef createChef(@RequestBody ChefDTO chefDTO)
    {
        return chefService.createChef(chefDTO);
    }

//    @PutMapping("/{id}")
//    public Chef updateChef(@PathVariable("id") long id, ChefDTO chefDTO)
//    {
//        return chefService.updateChef(id, chefDTO);
//    }

    @DeleteMapping("/{id}")
    public String deleteChef(@PathVariable("id") long id)
    {
        return chefService.deleteChef(id);
    }





}
