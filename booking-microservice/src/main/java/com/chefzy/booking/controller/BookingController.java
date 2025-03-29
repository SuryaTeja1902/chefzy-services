package com.chefzy.booking.controller;

import com.chefzy.booking.api.BookingAPI;
import com.chefzy.booking.dto.BookingDTO;
import com.chefzy.booking.dto.BookingResponseDTO;
import com.chefzy.booking.entity.Booking;
import com.chefzy.booking.mapper.BookingMapper;
import com.chefzy.booking.service.BookingService;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
@Slf4j
public class BookingController implements BookingAPI {

    @Autowired
    private BookingService bookingService;

    @Override
    public ResponseEntity<BookingResponseDTO> addChefBooking(Long chef_id, BookingDTO bookingDTO) {

        log.info("Received request to create a new booking with chef id");
        try{
            Booking booking = bookingService.addChefBooking(chef_id, bookingDTO);
            log.info("Chef Booking created with  ID - {}", chef_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(BookingMapper.mapBookingToBookingResponseDTO(booking));
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<BookingResponseDTO> addCatererBooking(Long caterer_id, BookingDTO bookingDTO) {

        log.info("Received request to create a new booking with caterer id");
        try{
            Booking booking = bookingService.addCatererBooking(caterer_id, bookingDTO);
            log.info("Caterer Booking created with  ID - {}", caterer_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(BookingMapper.mapBookingToBookingResponseDTO(booking));
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }

    @Override
    public ResponseEntity<?> getChefBookingById(Long chef_id) {
        log.info("Received request to get chef booking with ID - {}", chef_id);
        try{
            Booking booking = bookingService.getChefBookingById(chef_id);
            return ResponseEntity.status(HttpStatus.OK).body(BookingMapper.mapBookingToBookingResponseDTO(booking));
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    @Override
    public ResponseEntity<?> getCatererBookingById(Long caterer_id) {
        log.info("Received request to get caterer booking with ID - {}", caterer_id);
        try{
            Booking booking = bookingService.getCatererBookingById(caterer_id);
            return ResponseEntity.status(HttpStatus.OK).body(BookingMapper.mapBookingToBookingResponseDTO(booking));
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ArrayList<BookingResponseDTO>> getAllBookingsbyConsumerId(Long consumer_id) throws ValidationException {
        log.info("Received request to get all bookings with consumer ID - {}", consumer_id);
        try{
            ArrayList<BookingResponseDTO> allBookings = (ArrayList<BookingResponseDTO>) bookingService.getAllBookingsByConsumerId(consumer_id);
            return ResponseEntity.status(HttpStatus.OK).body(allBookings);
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ArrayList<BookingResponseDTO>> getAllBookingsbyChefId(Long chef_id) throws ValidationException {
        log.info("Received request to get all bookings by chef ID - {}", chef_id);
        try{
            ArrayList<BookingResponseDTO> allBookings = (ArrayList<BookingResponseDTO>) bookingService.getAllBookingsByChefId(chef_id);
            return ResponseEntity.status(HttpStatus.OK).body(allBookings);
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ArrayList<BookingResponseDTO>> getAllBookingsbyCatererId(Long caterer_id) throws ValidationException {
        log.info("Received request to get all bookings by caterer ID - {}", caterer_id);
        try{
            ArrayList<BookingResponseDTO> allBookings = (ArrayList<BookingResponseDTO>) bookingService.getAllBookingsByCatererId(caterer_id);
            return ResponseEntity.status(HttpStatus.OK).body(allBookings);
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    }

