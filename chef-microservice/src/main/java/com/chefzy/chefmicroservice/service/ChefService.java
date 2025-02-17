package com.chefzy.chefmicroservice.service;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.repository.ChefRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ChefService {

    private final ChefRepo chefRepo;

    public ChefService(ChefRepo chefRepo) {
        this.chefRepo = chefRepo;
    }

    public List<Chef> getAllChefs() {
        return chefRepo.findAll();
    }

    public Optional<Chef> getChefById(Long id) {
        return chefRepo.findById(id);
    }

    public Chef createChef(ChefDTO chefDTO)
    {
        Chef chef = new Chef(chefDTO);
        log.info("Chef created with  ID - {}",chef.getId());
        return chefRepo.save(chef);
    }

    public Chef updateChef(Long id, ChefDTO chefDTO)
    {
        Chef chef = new Chef(chefDTO);
        chef.setId(id);
        return chefRepo.save(chef);
    }

    public String deleteChef(long id)
    {
        chefRepo.deleteById(id);
        return "Chef deleted";
    }

}