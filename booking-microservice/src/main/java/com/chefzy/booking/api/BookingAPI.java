package com.chefzy.booking.api;

import com.chefzy.booking.dto.BookingDTO;
import com.chefzy.booking.dto.BookingResponseDTO;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

public interface BookingAPI {

    @PostMapping("/chef/{chef_id}")
    ResponseEntity<BookingResponseDTO> addChefBooking(@PathVariable("chef_id") Long chef_id, @RequestBody @Validated BookingDTO bookingDTO);

    @PostMapping("/caterer/{caterer_id}")
    ResponseEntity<BookingResponseDTO> addCatererBooking(@PathVariable("caterer_id") Long caterer_id, @RequestBody @Validated BookingDTO bookingDTO);

    @GetMapping("/chef/{chef_id}")
    ResponseEntity<?> getChefBookingById(@PathVariable("chef_id") Long chef_id) throws ValidationException;

    @GetMapping("/caterer/{caterer_id}")
    ResponseEntity<?> getCatererBookingById(@PathVariable("caterer_id") Long caterer_id) throws ValidationException;

    @GetMapping("/consumer/{consumer_id}/all")
    ResponseEntity<ArrayList<BookingResponseDTO>> getAllBookingsByConsumerId(@PathVariable("consumer_id") Long consumer_id) throws ValidationException;

    @GetMapping("/chef/{chef_id}/all")
    ResponseEntity<ArrayList<BookingResponseDTO>> getAllBookingsByChefId(@PathVariable("chef_id") Long chef_id) throws ValidationException;

    @GetMapping("/caterer/{caterer_id}/all")
    ResponseEntity<ArrayList<BookingResponseDTO>> getAllBookingsByCatererId(@PathVariable("caterer_id") Long caterer_id) throws ValidationException;


}