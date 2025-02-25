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

/**
 * Service class for managing chef-related operations.
 * <p>
 * This class handles the business logic for creating, retrieving, updating, and deleting chefs.
 * It interacts with the ChefRepo for database operations and uses ChefMapper for data transformations.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ChefService {

    private final ChefRepo chefRepo;

    /**
     * Retrieves a list of all chefs.
     * <p>
     * This method handles the retrieval of all chefs from the database.
     * It is marked as read-only to ensure no modifications are made to the database.
     *
     * @return a list of Chef objects representing all chefs in the database.
     */
    @Transactional(readOnly = true)
    public List<Chef> getAllChefs() {
        return chefRepo.findAll();
    }

    /**
     * Retrieves a chef by their ID.
     * <p>
     * This method handles the retrieval of a chef's information by their ID.
     * It is marked as read-only to ensure no modifications are made to the database.
     *
     * @param id the ID of the chef to retrieve.
     * @return an Optional containing the Chef object if found, or an empty Optional if not found.
     * @throws ValidationException if the chef ID is invalid or not found.
     */
    @Transactional(readOnly = true)
    public Optional<Chef> getChefById(Long id) throws ValidationException {
        chefRepo.findById(id).orElseThrow(() -> new ValidationException("Chef with ID " + id + " not found"));
        return chefRepo.findById(id);
    }

    /**
     * Creates a new chef.
     * <p>
     * This method handles the creation of a new chef in the database.
     * It logs the creation request, maps the ChefDTO to a Chef entity, and saves it to the database.
     *
     * @param chefDTO the data transfer object containing the details of the chef to be created.
     * @return the created Chef object.
     * @throws ValidationException if the chef data is invalid.
     */
    @Transactional
    public Chef createChef(ChefDTO chefDTO) throws ValidationException {
        Chef chef;
        try {
            chef = ChefMapper.mapToChef(chefDTO);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Invalid Chef data");
        }
        return chefRepo.save(chef);
    }

    /**
     * Updates an existing chef.
     * <p>
     * This method handles the update of an existing chef in the database.
     * It retrieves the existing chef, updates its details from the ChefDTO, and saves the updated chef to the database.
     *
     * @param id      the ID of the chef to update.
     * @param chefDTO the data transfer object containing the updated details of the chef.
     * @throws ValidationException if the chef ID is invalid or the chef is not found.
     */
    @Transactional
    public void updateChef(Long id, ChefDTO chefDTO) throws ValidationException {
        Chef existingchef = chefRepo.findById(id).orElseThrow(() -> new ValidationException("Chef with ID " + id + " not found"));
        ChefMapper.updateChefFromDTO(existingchef, chefDTO);
        chefRepo.save(existingchef);
    }

    /**
     * Deletes an existing chef.
     * <p>
     * This method handles the deletion of an existing chef from the database.
     * It retrieves the chef by ID and deletes it from the database.
     *
     * @param id the ID of the chef to delete.
     * @throws ValidationException if the chef ID is invalid or the chef is not found.
     */
    @Transactional
    public void deleteChef(long id) throws ValidationException {
        chefRepo.findById(id).orElseThrow(() -> new ValidationException("Chef with ID " + id + " not found"));
        chefRepo.deleteById(id);
    }
}