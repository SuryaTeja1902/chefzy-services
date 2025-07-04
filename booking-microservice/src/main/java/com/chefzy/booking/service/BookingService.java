package com.chefzy.booking.service;

import com.chefzy.booking.dto.BookingDTO;
import com.chefzy.booking.dto.BookingResponseDTO;
import com.chefzy.booking.entity.Booking;
import com.chefzy.booking.enums.BookingStatus;
import com.chefzy.booking.mapper.BookingMapper;
import com.chefzy.booking.repository.BookingRepo;
import com.chefzy.cateringmicroservice.repository.CatererRepo;
import com.chefzy.chefmicroservice.repository.ChefRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.*;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookingService {

    private final BookingRepo bookingRepo;
    private final ChefRepo chefRepo;
    private final CatererRepo catererRepo;

    public BookingService(BookingRepo bookingRepo, ChefRepo chefRepo, CatererRepo catererRepo) {
        this.bookingRepo = bookingRepo;
        this.chefRepo = chefRepo;
        this.catererRepo = catererRepo;
    }

    @Transactional
    public Booking addChefBooking(long chefId, BookingDTO bookingDTO) throws ValidationException {

        if (!chefRepo.existsById(chefId)) {
            throw new ValidationException("Chef with ID " + chefId + " not found.");
        }

        Booking booking;

        try {
            booking = BookingMapper.mapBookingDtoToBooking(bookingDTO);
            booking.setChefId(chefId);
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            booking.setCreateTime(LocalDateTime.now());

            // TODO - Handle Payment status effectively
//            booking.setPaymentStatus(PaymentStatus.SUCCESS);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Invalid Chef Booking Data!");
        }

        return bookingRepo.save(booking);
    }

    @Transactional
    public Booking addCatererBooking(Long catererId, BookingDTO bookingDTO) throws ValidationException {

        if (!catererRepo.existsById(catererId)) {
            throw new ValidationException("Caterer with ID " + catererId + " not found.");
        }

        Booking booking;

        try {
            booking = BookingMapper.mapBookingDtoToBooking(bookingDTO);
            booking.setCatererId(catererId);
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            booking.setCreateTime(LocalDateTime.now());

            // TODO - Handle Payment status effectively
//            booking.setPaymentStatus(PaymentStatus.SUCCESS);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Invalid Caterer Booking Data!");
        }

        return bookingRepo.save(booking);
    }

    @Transactional(readOnly = true)
    public Booking getChefBookingById(Long chef_id) throws ValidationException {

        try {
            return bookingRepo.findBookingByChefId(chef_id);
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }

    @Transactional(readOnly = true)
    public Booking getCatererBookingById(Long caterer_id) throws ValidationException {

        try {
            return bookingRepo.findBookingByCatererId((caterer_id));
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }

    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAllBookingsByConsumerId(Long consumer_id) throws ValidationException {

        try {
            ArrayList<BookingResponseDTO> allBookingsResponseDTO = new ArrayList<>();
            List<Booking> allBookings = bookingRepo.findAllByConsumerId(consumer_id);

            for (Booking booking : allBookings) {
                BookingResponseDTO responseDTO = BookingMapper.mapBookingToBookingResponseDTO(booking);
                allBookingsResponseDTO.add(responseDTO);
            }

            return allBookingsResponseDTO;
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }

    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAllBookingsByChefId(Long chef_id) throws ValidationException {

        try {
            ArrayList<BookingResponseDTO> allBookingsResponseDTO = new ArrayList<>();
            List<Booking> allBookings = bookingRepo.findAllByChefId(chef_id);

            for (Booking booking : allBookings) {
                BookingResponseDTO responseDTO = BookingMapper.mapBookingToBookingResponseDTO(booking);
                allBookingsResponseDTO.add(responseDTO);
            }

            return allBookingsResponseDTO;
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }

    @Transactional(readOnly = true)
    public List<BookingResponseDTO> getAllBookingsByCatererId(Long caterer_id) throws ValidationException {

        try {
            ArrayList<BookingResponseDTO> allBookingsResponseDTO = new ArrayList<>();
            List<Booking> allBookings = bookingRepo.findAllByCatererId(caterer_id);

            for (Booking booking : allBookings) {
                BookingResponseDTO responseDTO = BookingMapper.mapBookingToBookingResponseDTO(booking);
                allBookingsResponseDTO.add(responseDTO);
            }

            return allBookingsResponseDTO;
        } catch (RuntimeException exception) {
            log.error(exception.getMessage());
            throw new ValidationException("Error while getting details!");
        }

    }
}
