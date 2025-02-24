package com.chefzy.cateringmicroservice.service;

import com.chefzy.cateringmicroservice.dto.CatererDTO;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.chefzy.cateringmicroservice.mapper.CatererMapper;
import com.chefzy.cateringmicroservice.repository.CatererRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import jakarta.xml.bind.ValidationException;


@Service
@Slf4j
public class CateringService {

    private final CatererRepo catererRepo;

    public CateringService(CatererRepo catererRepo) {
        this.catererRepo = catererRepo;
    }

    @Transactional(readOnly = true)
    public List<Caterer> getAllCaterers()
    {
        return catererRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Caterer> getCatererById(long id)
    {
        return catererRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Caterer> getCatererByEvent(String eventType)
    {
        return catererRepo.findAllCaterersByEvent(eventType);
    }

    @Transactional
    public Caterer createCaterer(CatererDTO catererDTO) throws ValidationException {
        Caterer caterer;
        try {
            caterer = CatererMapper.mapToCaterer(catererDTO);
        }
        catch (JsonProcessingException exception){

            log.error(exception.getMessage());
            throw new ValidationException("Invalid Chef data");
        }
        return catererRepo.save(caterer);
    }
    @Transactional
    public Caterer updateCaterer(long id, CatererDTO catererDTO) throws JsonProcessingException {
        Caterer existingcaterer  = catererRepo.findById(id).orElseThrow(() -> new RuntimeException("Caterer with ID " + id + " not found"));
        CatererMapper.updateCatererFromDTO(existingcaterer, catererDTO);
        return catererRepo.save(existingcaterer);
    }

    @Transactional
    public void deleteCaterer(long id) {
        catererRepo.deleteById(id);
    }
}
