package com.chefzy.cateringmicroservice.controller;

import com.chefzy.cateringmicroservice.api.CateringAPI;
import com.chefzy.cateringmicroservice.dto.CatererDTO;
import com.chefzy.cateringmicroservice.dto.CatererResponseDTO;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.chefzy.cateringmicroservice.mapper.CatererMapper;
import com.chefzy.cateringmicroservice.service.CateringService;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing caterers.
 * This controller provides endpoints for creating, retrieving, updating, and deleting caterers.
 * It implements the CateringAPI interface.
 */
@RestController
@RequestMapping("/api/v1/caterers")
@Slf4j
public class CateringController implements CateringAPI {

    private final CateringService cateringService;

    public CateringController(CateringService cateringService) {
        this.cateringService = cateringService;
    }

    /**
     * Retrieves a list of all caterers.
     *
     * @return a list of all caterers
     */
    @Override
    public List<Caterer> getAllCaterers() {
        log.info("Received request to get all caterers");
        return cateringService.getAllCaterers();
    }

    /**
     * Retrieves a caterer by their ID.
     *
     * @param id the ID of the caterer to retrieve
     * @return a ResponseEntity containing the caterer if found, or a status of NOT_FOUND if not found,
     *         or a status of BAD_REQUEST if there is a validation error,
     *         or a status of INTERNAL_SERVER_ERROR if there is an unexpected error
     */
    @Override
    public ResponseEntity<Caterer> getCatererById(long id) {
        log.info("Received request to get caterer info with ID - {}", id);
        try {
            Optional<Caterer> caterer = cateringService.getCatererById(id);
            return ResponseEntity.ok(caterer.get());
        }
        catch (RuntimeException ex) {
            log.error("Caterer with ID - {} NOT found", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a list of caterers by event type.
     *
     * @param eventType the type of event to filter caterers by
     * @return a ResponseEntity containing the list of caterers for the specified event type,
     *         or a status of BAD_REQUEST if there is a validation error,
     *         or a status of INTERNAL_SERVER_ERROR if there is an unexpected error
     */
    @Override
    public ResponseEntity<List<Caterer>> getCatererByEvent(String eventType) {
        log.info("Received request to get caterers for event type - {}", eventType);
        try {
            List<Caterer> caterers = cateringService.getCatererByEvent(eventType);
            return ResponseEntity.ok(caterers);
        } catch (ValidationException exception) {
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception exception) {
            log.error("Unexpected error: {}", exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Creates a new caterer.
     *
     * @param catererDTO the data transfer object containing the details of the caterer to create
     * @return a ResponseEntity containing the created caterer, or a status of BAD_REQUEST if there is a validation error
     */
    @Override
    public ResponseEntity<CatererResponseDTO> createCaterer(CatererDTO catererDTO) {
        log.info("Received request to create a new caterer");
        try {
            Caterer caterer = cateringService.createCaterer(catererDTO);
            log.info("Caterer created with  ID - {}", caterer.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(CatererMapper.mapToCatererResponseDTO(caterer));
        } catch (ValidationException exception) {
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing caterer.
     *
     * @param id the ID of the caterer to update
     * @param catererDTO the data transfer object containing the updated details of the caterer
     * @return a ResponseEntity with a status of OK if the update is successful,
     *         or a status of BAD_REQUEST if there is a validation error
     */
    @Override
    public ResponseEntity<String> updateCaterer(long id, CatererDTO catererDTO) {
        log.info("Received request to update caterer with ID - {}", id);
        try {
            cateringService.updateCaterer(id, catererDTO);
            log.info("Successfully updated caterer with ID - {}", id);
        } catch (ValidationException ex) {
            log.error("Caterer with ID - {} NOT found", id);
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a caterer by their ID.
     *
     * @param id the ID of the caterer to delete
     * @return a ResponseEntity containing a success message if the deletion is successful,
     *         or a status of NOT_FOUND if the caterer is not found
     */
    @Override
    public ResponseEntity<String> deleteCaterer(long id) {
        log.info("Received request to delete caterer with ID - {}", id);
        try {
            cateringService.deleteCaterer(id);
            log.info("Successfully deleted caterer with ID - {}", id);
            return ResponseEntity.ok().body("Caterer deleted successfully");
        } catch (RuntimeException exception) {
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}