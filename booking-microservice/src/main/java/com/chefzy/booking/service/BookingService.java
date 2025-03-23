package com.chefzy.booking.service;


import com.chefzy.booking.dto.BookingDTO;
import com.chefzy.booking.entity.Booking;
import com.chefzy.booking.enums.BookingStatus;
import com.chefzy.booking.enums.PaymentStatus;
import com.chefzy.booking.mapper.BookingMapper;
import com.chefzy.booking.repository.BookingRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;


    @Transactional
    public Booking addCatererBooking(Long catererId, BookingDTO bookingDTO) throws ValidationException {

        Booking booking;

        try {
            booking = BookingMapper.mapBookingDtoToBooking(bookingDTO);
            booking.setCatererId(catererId);
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            booking.setCreateTime(LocalDateTime.now());
//            booking.setPaymentStatus(PaymentStatus.SUCCESS);
        }
        catch (JsonProcessingException exception)
        {
            log.error(exception.getMessage());
            throw new ValidationException("Invalid Caterer Booking Data!");
        }

        return bookingRepo.save(booking);
    }

    @Transactional
    public Booking addChefBooking(long chefId, BookingDTO bookingDTO) throws ValidationException {

        Booking booking;

        try {
            booking = BookingMapper.mapBookingDtoToBooking(bookingDTO);
            booking.setChefId(chefId);
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            booking.setCreateTime(LocalDateTime.now());
        }
        catch (JsonProcessingException exception)
        {
            log.error(exception.getMessage());
            throw new ValidationException("Invalid Chef Booking Data!");
        }

        return bookingRepo.save(booking);
    }
}
