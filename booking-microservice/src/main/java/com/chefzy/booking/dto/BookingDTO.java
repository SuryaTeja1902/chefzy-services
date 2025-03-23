package com.chefzy.booking.dto;

import com.chefzy.booking.enums.BookingStatus;
import com.chefzy.booking.enums.PaymentStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class BookingDTO {

    private Long consumerId;
    private PaymentStatus paymentStatus;
    private Double totalAmount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
