package com.chefzy.booking.entity;


import com.chefzy.booking.enums.BookingStatus;
import com.chefzy.booking.enums.PaymentStatus;
import com.chefzy.cateringmicroservice.entity.Caterer;
import com.chefzy.chefmicroservice.entity.Chef;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consumer_id")
    private Long consumerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "chef_id")
    private Long chefId;

    @Column(name = "caterer_id")
    private Long catererId;


}
