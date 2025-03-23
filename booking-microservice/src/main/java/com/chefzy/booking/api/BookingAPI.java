package com.chefzy.booking.api;

import com.chefzy.booking.dto.BookingDTO;
import com.chefzy.booking.dto.BookingResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookingAPI {

    @PostMapping("/chef/{chef_id}")
    ResponseEntity<BookingResponseDTO> addChefBooking(@PathVariable("chef_id") long chef_id, @RequestBody @Validated BookingDTO bookingDTO);

    @PostMapping("/caterer/{caterer_id}")
    ResponseEntity<BookingResponseDTO> addCatererBooking(@PathVariable("caterer_id") long caterer_id, @RequestBody @Validated BookingDTO bookingDTO);



}
