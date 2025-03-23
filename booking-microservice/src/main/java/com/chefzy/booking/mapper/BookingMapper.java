package com.chefzy.booking.mapper;

import com.chefzy.booking.dto.BookingDTO;
import com.chefzy.booking.dto.BookingResponseDTO;
import com.chefzy.booking.entity.Booking;
import com.chefzy.booking.enums.BookingStatus;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDateTime;

public class BookingMapper {

    public static Booking mapBookingDtoToBooking(BookingDTO bookingDTO) throws JsonProcessingException {
        Booking booking = new Booking();

        booking.setConsumerId(bookingDTO.getConsumerId());
        booking.setUpdateTime(LocalDateTime.now());
        booking.setTotalAmount(bookingDTO.getTotalAmount());
        booking.setPaymentStatus(bookingDTO.getPaymentStatus());


        return booking;
    }

    public static BookingResponseDTO mapBookingToBookingDTO(Booking booking) {

        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setConsumerId(booking.getConsumerId());
        bookingResponseDTO.setBookingStatus(booking.getBookingStatus());
        bookingResponseDTO.setTotalAmount(booking.getTotalAmount());
        bookingResponseDTO.setCreateTime(booking.getCreateTime());
        bookingResponseDTO.setId(booking.getId());
        bookingResponseDTO.setPaymentStatus(booking.getPaymentStatus());
        bookingResponseDTO.setChefId(booking.getChefId());
        bookingResponseDTO.setCatererId(booking.getCatererId());
        bookingResponseDTO.setUpdateTime(booking.getUpdateTime());

        return bookingResponseDTO;

    }


}
