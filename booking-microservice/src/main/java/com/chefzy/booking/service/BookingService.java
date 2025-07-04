package com.chefzy.booking.service;


import com.chefzy.booking.dto.BookingDTO;
import com.chefzy.booking.dto.BookingResponseDTO;
import com.chefzy.booking.entity.Booking;
import com.chefzy.booking.enums.BookingStatus;
import com.chefzy.booking.mapper.BookingMapper;
import com.chefzy.booking.repository.BookingRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    @Transactional
    public Booking getChefBookingById(Long chef_id) throws ValidationException {

        try
        {
            Booking booking = bookingRepo.findBookingByChefId(chef_id);
            return booking;
        }
        catch (RuntimeException exception)
        {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }


    @Transactional
    public Booking getCatererBookingById(Long caterer_id) throws ValidationException {

        try
        {
            return bookingRepo.findBookingByCatererId((caterer_id));
        }
        catch (RuntimeException exception)
        {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }

    @Transactional
    public List<BookingResponseDTO> getAllBookingsByConsumerId(Long consumer_id) throws ValidationException {

        try
        {   ArrayList<BookingResponseDTO> allBookingsResponseDTO = new ArrayList<>();
            List<Booking>  allBookings = bookingRepo.findAllByConsumerId(consumer_id);

            for(Booking booking : allBookings)
            {
                BookingResponseDTO responseDTO = BookingMapper.mapBookingToBookingResponseDTO(booking);
                allBookingsResponseDTO.add(responseDTO);
            }

            return allBookingsResponseDTO;
        }
        catch (RuntimeException exception)
        {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }

    @Transactional
    public List<BookingResponseDTO> getAllBookingsByChefId(Long chef_id) throws ValidationException {

        try
        {   ArrayList<BookingResponseDTO> allBookingsResponseDTO = new ArrayList<>();
            List<Booking>  allBookings = bookingRepo.findAllByChefId(chef_id);

            for(Booking booking : allBookings)
            {
                BookingResponseDTO responseDTO = BookingMapper.mapBookingToBookingResponseDTO(booking);
                allBookingsResponseDTO.add(responseDTO);
            }

            return allBookingsResponseDTO;
        }
        catch (RuntimeException exception)
        {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }

    @Transactional
    public List<BookingResponseDTO> getAllBookingsByCatererId(Long caterer_id) throws ValidationException {

        try
        {   ArrayList<BookingResponseDTO> allBookingsResponseDTO = new ArrayList<>();
            List<Booking>  allBookings = bookingRepo.findAllByCatererId(caterer_id);

            for(Booking booking : allBookings)
            {
                BookingResponseDTO responseDTO = BookingMapper.mapBookingToBookingResponseDTO(booking);
                allBookingsResponseDTO.add(responseDTO);
            }

            return allBookingsResponseDTO;
        }
        catch (RuntimeException exception)
        {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }
}
