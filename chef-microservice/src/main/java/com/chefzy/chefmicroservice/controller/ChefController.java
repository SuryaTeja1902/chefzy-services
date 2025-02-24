package com.chefzy.chefmicroservice.controller;

import com.chefzy.chefmicroservice.api.ChefAPI;
import com.chefzy.chefmicroservice.dto.ChefDTO;
import com.chefzy.chefmicroservice.dto.ChefResponseDTO;
import com.chefzy.chefmicroservice.entity.Chef;
import com.chefzy.chefmicroservice.mapper.ChefMapper;
import com.chefzy.chefmicroservice.service.ChefService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/chefs")
@Slf4j
public class ChefController implements ChefAPI {

    private final ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    /**
     * Retrieves a list of all chefs.
     * <p>
     * This method handles the HTTP GET request to retrieve all chefs from the database.
     * It logs the request and delegates the retrieval process to the ChefService.
     *
     * @return a list of Chef objects representing all chefs in the database.
     */
    @Override
    public List<Chef> getAllChefs() {
        log.info("Received request to get all chefs");
        return chefService.getAllChefs();
    }

    /**
     * Retrieves a chef by their ID.
     * <p>
     * This method handles the HTTP GET request to retrieve a chef's information by their ID.
     * It logs the request, attempts to retrieve the chef from the ChefService, and handles any validation exceptions.
     *
     * @param id the ID of the chef to retrieve.
     * @return an Optional containing an Optional of the Chef object if found,
     * or an empty Optional if not found or if a validation exception occurs.
     * @throws ValidationException if the chef ID is invalid.
     */
    @Override
    public Optional<Optional<Chef>> getChefById(long id) throws ValidationException {
        log.info("Received request to get chef info with ID - {}", id);
        try {
            chefService.getChefById(id);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return Optional.empty();
        }
        return Optional.ofNullable(chefService.getChefById(id));
    }

    /**
     * Creates a new chef.
     * <p>
     * This method handles the HTTP POST request to create a new chef in the database.
     * It logs the creation request, delegates the creation process to the ChefService,
     * and returns the created chef's information.
     *
     * @param chefDTO the data transfer object containing the details of the chef to be created.
     * @return a ResponseEntity containing the ChefResponseDTO of the created chef and the HTTP status code.
     * @throws ValidationException if the chef data is invalid.
     */
    @Override
    public ResponseEntity<ChefResponseDTO> createChef(ChefDTO chefDTO) throws ValidationException {
        Chef chef = chefService.createChef(chefDTO);
        log.info("Chef created with  ID - {}", chef.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ChefMapper.mapToChefResponseDTO(chef));
    }

    /**
     * Updates an existing chef.
     * <p>
     * This method handles the HTTP PUT request to update an existing chef in the database.
     * It logs the update request, delegates the update process to the ChefService,
     * and returns the appropriate HTTP response.
     *
     * @param id      the ID of the chef to update.
     * @param chefDTO the data transfer object containing the updated details of the chef.
     * @return a ResponseEntity containing a success message and the HTTP status code, or an error message if the chef is not found.
     */
    @Override
    public ResponseEntity<String> updateChef(long id, ChefDTO chefDTO) {
        log.info("Received request to update chef with ID - {}", id);
        try {
            chefService.updateChef(id, chefDTO);
            log.info("Successfully updated chef with ID - {}", id);
        } catch (ValidationException ex) {
            log.error("Chef with ID - {} NOT found", id);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes an existing chef.
     * <p>
     * This method handles the HTTP DELETE request to delete an existing chef from the database.
     * It logs the deletion request, delegates the deletion process to the ChefService,
     * and returns the appropriate HTTP response.
     *
     * @param id the ID of the chef to delete.
     * @return a ResponseEntity containing a success message and the HTTP status code, or an error message if the chef is not found.
     */
    @Override
    public ResponseEntity<String> deleteChef(long id) {
        log.info("Received request to delete chef");
        try {
            chefService.deleteChef(id);
        } catch (ValidationException ex) {
            log.error("Chef with ID - {} NOT found", id);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}