package com.chefzy.chefmicroservice.service;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.mapper.ChefMapper;
import com.chefzy.chefmicroservice.repository.ChefRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
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

    @Transactional
    public Chef createChef(ChefDTO chefDTO) throws ValidationException {
        Chef chef;
        try {
            chef = ChefMapper.mapToChef(chefDTO);
        }
        catch (JsonProcessingException exception){

            log.error(exception.getMessage());
            throw new ValidationException("Invalid Chef data");
        }
        return chefRepo.save(chef);
    }

    @Transactional
    public Chef updateChef(Long id, ChefDTO chefDTO) throws JsonProcessingException {
        Chef existingchef  = chefRepo.findById(id).orElseThrow(() -> new RuntimeException("Chef with ID " + id + " not found"));
        ChefMapper.updateChefFromDTO(existingchef, chefDTO);
        return chefRepo.save(existingchef);
    }

    @Transactional
    public String deleteChef(long id)
    {
        chefRepo.deleteById(id);
        return "Chef deleted";
    }

}