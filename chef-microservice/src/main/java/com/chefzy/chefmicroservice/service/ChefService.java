package com.chefzy.chefmicroservice.service;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.repository.ChefRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {

    @Autowired
    ChefRepo chefRepo;

    public List<Chef> getAllChefs() {
        return chefRepo.findAll();
    }

    public Optional<Chef> getChefById(Long id) {
        return chefRepo.findById(id);
    }

    public Chef createChef(ChefDTO chefDTO)
    {
        Chef chef = new Chef(chefDTO);
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
