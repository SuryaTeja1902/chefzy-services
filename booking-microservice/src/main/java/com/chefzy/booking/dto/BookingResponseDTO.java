package com.chefzy.booking.dto;

import com.chefzy.booking.entity.Booking;
import com.chefzy.booking.enums.BookingStatus;
import com.chefzy.booking.enums.PaymentStatus;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.chefzy.chefmicroservice.entity.Chef;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class BookingResponseDTO {

    private Long id;

    private Long consumerId;

    private BookingStatus bookingStatus;

    private PaymentStatus paymentStatus;

    private Double totalAmount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long chefId;

    private Long catererId;
}
