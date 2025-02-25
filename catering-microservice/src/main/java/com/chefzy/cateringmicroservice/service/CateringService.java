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

    /**
     * Retrieves a list of all caterers.
     * <p>
     * This method is transactional and read-only, meaning it will not modify the database.
     * It fetches all caterers from the repository.
     *
     * @return a list of all caterers
     */
    @Transactional(readOnly = true)
    public List<Caterer> getAllCaterers() {
        return catererRepo.findAll();
    }

    /**
     * Retrieves a caterer by their ID.
     * <p>
     * This method is transactional and read-only, meaning it will not modify the database.
     * It fetches the caterer from the repository by their ID.
     *
     * @param id the ID of the caterer to retrieve
     * @return an Optional containing the caterer if found, or an empty Optional if not found
     */
    @Transactional(readOnly = true)
    public Optional<Caterer> getCatererById(long id) {
        catererRepo.findById(id).orElseThrow(() -> new RuntimeException("Caterer with ID " + id + " not found"));
        return catererRepo.findById(id);
    }

    /**
     * Retrieves a list of caterers by event type.
     * <p>
     * This method is transactional and read-only, meaning it will not modify the database.
     * It fetches all caterers from the repository that match the specified event type.
     *
     * @param eventType the type of event to filter caterers by
     * @return a list of caterers that match the specified event type
     * @throws ValidationException if there is an error retrieving caterers for the specified event type
     */
    @Transactional(readOnly = true)
    public List<Caterer> getCatererByEvent(String eventType) throws ValidationException {
        try {
            return catererRepo.findAllCaterersByEvent(eventType);
        } catch (Exception e) {
            log.error("Error retrieving caterers for event type - {}", eventType, e);
            throw new ValidationException("Error retrieving caterers for event type: " + eventType);
        }
    }

    /**
     * Creates a new caterer.
     * <p>
     * This method is transactional and will modify the database.
     * It maps the provided CatererDTO to a Caterer entity and saves it to the repository.
     *
     * @param catererDTO the data transfer object containing the details of the caterer to create
     * @return the created caterer
     * @throws ValidationException if the provided caterer data is invalid
     */
    @Transactional
    public Caterer createCaterer(CatererDTO catererDTO) throws ValidationException {
        Caterer caterer;
        try {
            caterer = CatererMapper.mapToCaterer(catererDTO);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Invalid Caterer data");
        }
        return catererRepo.save(caterer);
    }

    /**
     * Updates an existing caterer.
     * <p>
     * This method is transactional and will modify the database.
     * It updates the existing caterer with the details provided in the CatererDTO.
     *
     * @param id the ID of the caterer to update
     * @param catererDTO the data transfer object containing the updated details of the caterer
     * @throws ValidationException if the provided caterer data is invalid
     */
    @Transactional
    public void updateCaterer(long id, CatererDTO catererDTO) throws ValidationException {
        Caterer existingcaterer = catererRepo.findById(id).orElseThrow(() -> new RuntimeException("Caterer with ID " + id + " not found"));
        try {
            CatererMapper.updateCatererFromDTO(existingcaterer, catererDTO);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Invalid Caterer data");
        }
        catererRepo.save(existingcaterer);
    }

    /**
     * Deletes an existing caterer.
     * <p>
     * This method is transactional and will modify the database.
     * It deletes the caterer with the specified ID from the repository.
     *
     * @param id the ID of the caterer to delete
     */
    @Transactional
    public void deleteCaterer(long id) {
        Caterer existingcaterer = catererRepo.findById(id).orElseThrow(() -> new RuntimeException("Caterer with ID " + id + " not found"));
        catererRepo.delete(existingcaterer);
    }
}