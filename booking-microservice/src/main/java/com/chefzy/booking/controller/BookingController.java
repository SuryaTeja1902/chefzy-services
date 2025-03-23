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

@RestController
@RequestMapping("api/v1/bookings")
@Slf4j
public class BookingController implements BookingAPI {

    @Autowired
    private BookingService bookingService;

    @Override
    public ResponseEntity<BookingResponseDTO> addChefBooking(long chef_id, BookingDTO bookingDTO) {

        log.info("Received request to create a new booking with chef id");
        try{
            Booking booking = bookingService.addChefBooking(chef_id, bookingDTO);
            log.info("Chef Booking created with  ID - {}", chef_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(BookingMapper.mapBookingToBookingDTO(booking));
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<BookingResponseDTO> addCatererBooking(long caterer_id, BookingDTO bookingDTO) {

        log.info("Received request to create a new booking with caterer id");
        try{
            Booking booking = bookingService.addCatererBooking(caterer_id, bookingDTO);
            log.info("Caterer Booking created with  ID - {}", caterer_id);
            return ResponseEntity.status(HttpStatus.CREATED).body(BookingMapper.mapBookingToBookingDTO(booking));
        }
        catch (ValidationException exception){
            log.error("Validation error: {}", exception.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

    }
}
