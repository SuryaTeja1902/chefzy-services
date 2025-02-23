package com.chefzy.chefmicroservice.service;

import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.mapper.ChefMapper;
import com.chefzy.chefmicroservice.repository.ChefRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChefService {

    private final ChefRepo chefRepo;

    @Transactional(readOnly = true)
    public List<Chef> getAllChefs() {
        return chefRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Chef> getChefById(Long id) throws ValidationException {
        chefRepo.findById(id).orElseThrow(() -> new ValidationException("Chef with ID " + id + " not found"));
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
    public void updateChef(Long id, ChefDTO chefDTO) throws ValidationException {
        Chef existingchef  = chefRepo.findById(id).orElseThrow(() -> new ValidationException("Chef with ID " + id + " not found"));
        ChefMapper.updateChefFromDTO(existingchef, chefDTO);
        chefRepo.save(existingchef);
    }

    @Transactional
    public void deleteChef(long id) throws ValidationException {
        chefRepo.findById(id).orElseThrow(() -> new ValidationException("Chef with ID " + id + " not found"));
        chefRepo.deleteById(id);
    }
}